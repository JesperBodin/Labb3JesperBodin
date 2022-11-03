package com.example.labb3jesperbodin;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller  {

    public Button squareButton;
    public Button circleButton;
    public Button pointButton;
    @FXML
    ComboBox<String> shapeComboBox = new ComboBox<>();
    @FXML
    ComboBox<String> sizeComboBox = new ComboBox<>();

    public Canvas canvas;

    public GraphicsContext context;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button rectangleButton;


    public void initialize(){
        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        context.setFill(Color.rgb(230,230,230));
        context.fillRect(0,0,500,600);
    }

    public void drawOnClick(MouseEvent mouseEvent){

        context.setFill(colorPicker.getValue());
        getShapeFromComboBox(mouseEvent);

    }

    private void getShapeFromComboBox(MouseEvent mouseEvent) {
        switch (shapeComboBox.getSelectionModel().getSelectedItem()){
            case "Rectangle":
                context.fillRect(mouseEvent.getX() -10, mouseEvent.getY() -10,40,20);
        }
    }
    public void drawNewRectangle(MouseEvent mouseEvent){
        context.setFill(colorPicker.getValue());
        context.fillRect(250,250,250,250);
    }
    public boolean rectangleButtonPressed(){

    }
}

