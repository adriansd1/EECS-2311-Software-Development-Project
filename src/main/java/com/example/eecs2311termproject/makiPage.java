package com.example.eecs2311termproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class makiPage {
    public static void display(){
        Stage makiStage = new Stage();
        VBox layout = new VBox(10);
        Button homeButton = new Button("Home");

        homeButton.setOnAction(e -> {
            makiStage.close();
        });

        layout.getChildren().addAll(homeButton);
        Scene scene = new Scene(layout, 300, 200);
        makiStage.setScene(scene);
        makiStage.show();
    }
}
