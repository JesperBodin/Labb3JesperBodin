package com.example.labb3jesperbodin.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Shape {

    private final ObjectProperty<Color> color = new SimpleObjectProperty<>();
    private final DoubleProperty xPosition = new SimpleDoubleProperty();
    private final DoubleProperty yPosition = new SimpleDoubleProperty();
    private final DoubleProperty size = new SimpleDoubleProperty();

    private final ObjectProperty<Color> borderColor = new SimpleObjectProperty<>();

    public Shape(Color color, double xPosition, double yPosition, double size) {
        setColor(color);
        setXPosition(xPosition);
        setYPosition(yPosition);
        setSize(size);
        setBorderColor(Color.TRANSPARENT);

    }

    public Shape (Shape shape){

    }

    public ObjectProperty<Color> borderColorProperty() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor.set(borderColor);
    }

    public Color getBorderColor() {
        return borderColor.get();
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public double getXPosition() {
        return xPosition.get();
    }

    public DoubleProperty xPositionProperty() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition.set(xPosition);
    }

    public double getYPosition() {
        return yPosition.get();
    }

    public DoubleProperty yPositionProperty() {
        return yPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition.set(yPosition);
    }

    public double getSize() {
        return size.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }





    public void draw(GraphicsContext graphicsContext){

    }

    public abstract boolean insideShapeCheck(double x, double y);


}
