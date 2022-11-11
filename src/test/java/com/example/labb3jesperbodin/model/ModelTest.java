package com.example.labb3jesperbodin.model;

import com.example.labb3jesperbodin.shapes.Circle;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ModelTest {

    Model model = new Model();

    @Test
    void testDefaultShapeValues(){

        var colorActual =new Model().getColor();
        var sizeActual = new Model().getSize();

        assertEquals(Color.RED,colorActual);
        assertEquals(50,sizeActual);

    }
    @Test
    void testInsideShapeCheck(){

        model.shapes.add(new Circle(Color.BLUE,75,75,50));

        double mousePositionX = 75.0;
        double mousePositionY = 75.0;

        var Expected = true;
        var Actual = false;


        for (var shape: model.shapes){
            if (shape.insideShapeCheck(mousePositionX,mousePositionY)){
                Actual = true;
            }
        }

        assertEquals(Expected,Actual);

    }

}