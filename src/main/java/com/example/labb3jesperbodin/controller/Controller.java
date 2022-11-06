package com.example.labb3jesperbodin.controller;

import com.example.labb3jesperbodin.model.*;
import com.example.labb3jesperbodin.shapes.Circle;
import com.example.labb3jesperbodin.shapes.Rectangle;
import com.example.labb3jesperbodin.shapes.Shape;
import com.example.labb3jesperbodin.shapes.Square;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    public Canvas canvas;
    public GraphicsContext context;
    public Model model;
    ObservableList<Shape> shapeObservableList = FXCollections.observableArrayList();
    @FXML
    ListView<Shape> listViewTest = new ListView<>(shapeObservableList); // model.shapes & ta bort Shapeobservablelist



    public void initialize() {
        model = new Model();
        SpinnerValueFactory<Integer> spinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200);
        spinner.setValueFactory(spinnerValue);

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        spinnerValue.valueProperty().bindBidirectional(model.sizeProperty());

        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        renderCanvas();

        listViewTest.setItems(model.shapes);
    }

    private void draw() {
        model.draw(context);
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        drawOnClick(mouseEvent);
        renderCanvas();
//        listViewTest.getSelectionModel();
        draw();
    }

    public void renderCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, 610, 713);
    }

    public void drawOnClick(MouseEvent mouseEvent) {
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
            System.out.println("AVMARKERAD");
        } else {
            model.setBorderColorOnSelected(shape);
            model.selectedShapes.add(shape);
            System.out.println("MARKERAD");
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
//        model.undoShapeDeque.addAll(model.selectedShapes);
        model.deleteSelectedShape();
        renderCanvas();
        draw();
    }

    public void undoLast() {
        model.undo();
        renderCanvas();
        draw();

    }

    public void changeColorOnSelectedShapes() {
        model.addToUndoDeque();
        model.changeColorOnShapes();
        renderCanvas();
        draw();

    }

    public void changeSizeOnSelectedShapes() {
        model.addToUndoDeque();
        model.changeSizeOnShapes();
        renderCanvas();
        draw();

    }
}


