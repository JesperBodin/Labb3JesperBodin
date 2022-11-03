package com.example.labb3jesperbodin;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;


public class Shape {

    private final ObjectProperty<Color> color = new SimpleObjectProperty<>();
    private final DoubleProperty xPosition = new SimpleDoubleProperty();
    private final DoubleProperty yPosition = new SimpleDoubleProperty();

    public Shape(){

    }

    public Shape(javafx.scene.shape.Shape shape) {
        setColor(getColor());
        setXPosition(getXPosition());
        setYPosition(getYPosition());
    }

    private Object getXPosition() {
    }

    public Color getColor(){
        return color.get();
    }
    public ObjectProperty<Color> colorProperty(){
        return color;
    }
    public void setColor(Color color){
        this.color.set(color);
    }
    public void getYPosition(){
        this.yPosition.set();
    }



}
