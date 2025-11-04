package org.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleThingy extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle();
        circle.setCenterX(150);
        circle.setCenterY(100);
        circle.setRadius(10);
        Pane root = new Pane(circle);
        Scene scene = new Scene(root, 300, 200);

        Paint blue = Paint.valueOf("blue");
        Paint black = Paint.valueOf("black");

        //myszka
        circle.setOnMouseEntered(e -> {
            circle.setStroke(blue);
            circle.setStrokeWidth(3);
        });

        // też myszka
        circle.setOnMouseExited(e -> {
            circle.setStroke(black);
            circle.setStrokeWidth(1);
        });

        //klawiaturka
        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ADD || e.getCode() == KeyCode.ENTER) {
                circle.setRadius(circle.getRadius() + 5);
            }
            if(e.getCode() == KeyCode.SUBTRACT || e.getCode() == KeyCode.BACK_SPACE){
                circle.setRadius(circle.getRadius()-5);
            }
            if(e.getCode() == KeyCode.RIGHT){
                circle.setCenterX(circle.getCenterX()+5);
            }
            if(e.getCode() == KeyCode.LEFT){
                circle.setCenterX(circle.getCenterX()-5);
            }
            if(e.getCode() == KeyCode.UP){
                circle.setCenterY(circle.getCenterY()-5);
            }
            if(e.getCode() == KeyCode.DOWN){
                circle.setCenterY(circle.getCenterY()+5);
            }
        });
        primaryStage.setTitle("kułko");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
    