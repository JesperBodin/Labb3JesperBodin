package com.example.labb3jesperbodin.controller;

import com.example.labb3jesperbodin.model.*;
import com.example.labb3jesperbodin.shapes.Circle;
import com.example.labb3jesperbodin.shapes.Rectangle;
import com.example.labb3jesperbodin.shapes.Shape;
import com.example.labb3jesperbodin.shapes.Square;
import com.example.labb3jesperbodin.svg.SVGWriter;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Controller {

    public CheckBox checkBox;
    public ColorPicker colorPicker;
    public Spinner<Integer> spinner;
    public Button deleteButton;
    public Button undoButton;
    public Button changeColorButton;
    public Button changeSizeButton;
    public Button squareButton;
    public Button rectangleButton;
    public Button circleButton;
    public Button saveButton;
    public Canvas canvas;
    public GraphicsContext context;
    public Model model;
    ObservableList<Shape> shapeObservableList = FXCollections.observableArrayList();

    public void initialize() {
        model = new Model();
        SpinnerValueFactory<Integer> spinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200);
        spinner.setValueFactory(spinnerValue);

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        spinnerValue.valueProperty().bindBidirectional(model.sizeProperty());

        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        renderCanvas();
        model.shapes.addListener(this::listChanged);
    }
    private void listChanged(Observable observable) {
        renderCanvas();
        draw();
    }
    public void renderCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, 610, 713);
    }
    private void draw() {
        model.draw(context);
    }





    public void canvasClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (checkBox.isSelected()) {
            for (var shape : model.shapes) {
                if (shape.insideShapeCheck(x, y)) {
                    selectShapes(shape);
                }
            }
        } else shapeType(x, y);
    }

    public void shapeType(double x, double y) {
        if (rectangleButton.isFocused()) {
            addRectangleToObservableList(x, y);

        } else if (circleButton.isFocused()) {
            addCircleToObservableList(x, y);

        } else if (squareButton.isFocused()) {
            addSquareToObservableList(x, y);
        }
    }


    private void selectShapes(Shape shape) {

        if (model.selectedShapes.contains(shape)) {
            model.setBorderColorOnDeselected(shape);
            model.selectedShapes.remove(shape);
        } else {
            model.setBorderColorOnSelected(shape);
            model.selectedShapes.add(shape);
        }

    }


    private void addSquareToObservableList(double x, double y) {
        model.shapes.add(new Square(model.getColor(), x, y, model.getSize()));
        shapeObservableList.add(new Square(model.getColor(), x, y, model.getSize()));

    }

    private void addCircleToObservableList(double x, double y) {
        model.shapes.add(new Circle(model.getColor(), x, y, model.getSize()));
        shapeObservableList.add(new Circle(model.getColor(), x, y, model.getSize()));
    }

    private void addRectangleToObservableList(double x, double y) {
        model.shapes.add(new Rectangle(model.getColor(), x, y, model.getSize()));
        shapeObservableList.add(new Rectangle(model.getColor(), x, y, model.getSize()));
    }

    public void deleteMarkedShapes() {
        model.addToUndoDeque();
        model.deleteSelectedShape();
    }
    public void undoLast() {
        model.undo();
    }

    public void changeColorOnSelectedShapes() {
        model.addToUndoDeque();
        model.changeColorOnShapes();
    }

    public void changeSizeOnSelectedShapes() {
        model.addToUndoDeque();
        model.changeSizeOnShapes();
    }

    public void saveToFile() {
        SVGWriter svgFile = new SVGWriter();
        svgFile.saveToFile(model);
    }
}


