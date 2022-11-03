package com.example.labb3jesperbodin.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class Model {

//    public Deque<Shape> shapes = new ArrayDeque<>();
    private final ObjectProperty<Color> color;

    private final ObjectProperty <Color> borderColor;
    private final ObjectProperty<Integer> size;

    public ObservableList<Shape> shapes;

    public ObservableList<Shape> selectedShapes;

    public Deque<Shape> undoShapeDeque;


    public Model(){
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
        this.borderColor = new SimpleObjectProperty<>(getBorderColor());
        this.selectedShapes = FXCollections.observableArrayList();
        this.undoShapeDeque = new ArrayDeque<>();
    }


    public void setBorderColorOnSelected(Shape shape){
        shape.setBorderColor(Color.BLACK);
    }
    public void setBorderColorOnDeselected(Shape shape){
        shape.setBorderColor(Color.WHITE);
    }

    public Color getBorderColor() {
        return Color.BLACK;
    }

    public ObjectProperty<Color> borderColorProperty() {
        return borderColor;
    }


    public ObjectProperty<Color> colorProperty() {
        return color;
    }
    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }
    public Color getColor(){
        return color.get();
    }
    public Integer getSize(){
        return size.get();
    }

    public void markSelectedShape(Shape shape){
        shape.setBorderColor(Color.BLACK);
    }

    public void deleteSelectedShape() {
        for (var shape : selectedShapes){
            shapes.remove(shape);
        }
    }
    public void changeColorOnShapes() {
        for (var shape : selectedShapes){
            shape.setColor(getColor());
        }
    }
    public void changeSizeOnShapes() {
        for (var shape : selectedShapes) {
            shape.setSize(getSize());
        }
    }
}
