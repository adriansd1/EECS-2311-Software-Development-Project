package com.example.eecs2311termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuHandler {
    @FXML
    private Label welcomeText;

    public void handleSushi(ActionEvent event){
        System.out.println("sushi button");

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}