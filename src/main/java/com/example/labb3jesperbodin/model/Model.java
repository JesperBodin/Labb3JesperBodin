package com.example.labb3jesperbodin.model;

import com.example.labb3jesperbodin.shapes.Shape;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayDeque;
import java.util.Deque;

public class Model {

    private final ObjectProperty<Color> color;
    private final ObjectProperty<Integer> size;
    public ObservableList<Shape> shapes;
    public ObservableList<Shape> selectedShapes;
    public Deque<ObservableList<Shape>> undoShapeDeque;



    public Model() {
        this.shapes = FXCollections.observableArrayList(
                shape -> new Observable[]{
                        shape.xPositionProperty(),
                        shape.yPositionProperty(),
                        shape.colorProperty(),
                        shape.sizeProperty(),
                        shape.borderColorProperty()
                }
        );
        this.color = new SimpleObjectProperty<>(Color.RED);
        this.size = new SimpleObjectProperty<>(50);
        this.selectedShapes = FXCollections.observableArrayList();
        this.undoShapeDeque = new ArrayDeque<>();

    }


    public void setBorderColorOnSelected(Shape shape) {
        shape.setBorderColor(Color.BLACK);
    }
    public void setBorderColorOnDeselected(Shape shape) {
        shape.setBorderColor(Color.WHITE);
    }
    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }

    public Color getColor() {
        return color.get();
    }

    public Integer getSize() {
        return size.get();
    }

    public void deleteSelectedShape() {
        for (var shape : selectedShapes) {
            shapes.remove(shape);
        }
    }
    public void changeColorOfShapes() {
        for (var shape : selectedShapes) {
            shape.setColor(getColor());
        }
    }
    public void changeSizeOfShapes() {
        for (var shape : selectedShapes) {
            shape.setSize(getSize());
        }
    }
    public void undo() {
        if (undoShapeDeque.isEmpty())
            return;

        shapes.clear();
        shapes.addAll(undoShapeDeque.removeLast());
    }
    public ObservableList<Shape> getTemporaryShapeList(){
        ObservableList<Shape> temporaryList = FXCollections.observableArrayList();

        for (var shape : shapes){
            temporaryList.add(shape.copyShape());
        }
        return temporaryList;
    }
    public void addToUndoDeque() {
        ObservableList<Shape> temporaryList = getTemporaryShapeList();
        undoShapeDeque.addLast(temporaryList);
    }
    public void draw(GraphicsContext context) {
        for (var shape : shapes) {
            shape.draw(context);
        }
    }

}
