package com.example.labb3jesperbodin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        Group root = new Group();

        Canvas canvas = new Canvas(400,300);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();



        stage.setTitle("Labb3, Jesper Bodin");
        stage.setScene(new Scene(root));
        stage.show();


    }




    public static void main(String[] args) {
        launch();
    }

}