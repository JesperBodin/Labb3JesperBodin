package com.example.labb3jesperbodin.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    public Circle(Color color, double xPosition, double yPosition, double size) {
        super(color, xPosition, yPosition, size);
    }

    public Circle(Shape shape) {
        super(shape);
    }

    public void draw(GraphicsContext context) {
        context.setFill(getBorderColor());
        context.fillOval(getXPosition() - getSize() / 2,
                getYPosition() - getSize() / 2, getSize(), getSize());

        context.setFill(this.getColor());
        context.fillOval(getXPosition() - getSize() / 2 + 2,
                getYPosition() - getSize() / 2 + 2, getSize() - 4, getSize() - 4);
    }

    @Override
    public boolean insideShapeCheck(double x, double y) {
        double xPosition = getXPosition() - getSize() / 2;
        double yPosition = getYPosition() - getSize() / 2;


        return x >= xPosition &&
                x <= xPosition + getSize()  &&
                y >= yPosition &&
                y <= yPosition + getSize();
    }

}
