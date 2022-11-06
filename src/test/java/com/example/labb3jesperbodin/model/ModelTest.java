package com.example.labb3jesperbodin.model;

import com.example.labb3jesperbodin.shapes.Circle;
import com.example.labb3jesperbodin.shapes.Shape;
import com.example.labb3jesperbodin.shapes.Square;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.labb3jesperbodin.shapes.Rectangle;

class ModelTest {

    Model model = new Model();

    @Test
    void addNewRectangleToShapesList(){

        model.shapes.add(new Rectangle(Color.BLUE,25,25,50));

        var actual = 1;
        var contains = model.shapes.size();

        assertEquals(actual, contains);

    }

    @Test
    void  addShapeFromShapeObservableListToTemporaryListInsideUndoDeque(){

        model.shapes.add(new Rectangle(Color.RED,25,25,50));
        model.shapes.add(new Circle(Color.RED,25,25,50));
        model.shapes.add(new Square(Color.RED,25,25,50));
        model.addToUndoDeque();

        var expected = 3;
        var actual = model.getTemporaryShapeList().size();

        assertEquals(expected,actual);

    }

    @Test
    void passLastShapeFromUndoDequeToShapesObservableList(){

//        model.shapes.add(new Rectangle(Color.GREEN,25,25,50));
        model.shapes.add(new Rectangle(Color.RED,25,25,50));
        model.shapes.add(new Circle(Color.RED,25,25,50));
        model.shapes.add(new Square(Color.RED,25,25,50));

        ObservableList<Shape> temporaryList = model.getTemporaryShapeList();
        model.undoShapeDeque.addLast(temporaryList);
        model.shapes.clear();
        model.shapes.addAll(temporaryList);

        var expected = 3;
        var actual =model.shapes.size();

        assertEquals(expected,actual);

    }


}