package com.example.eecs2311termproject;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginScreen {
    public static VBox createLoginScreen(MenuHandler menuHandler){
        VBox loginBox = new VBox();
        loginBox.setSpacing(10);

        //GridPane grid = new GridPane();
        //grid.setHgap(10);
        //grid.setVgap(10);

        TextField tableNumberField = new TextField();
        tableNumberField.setPromptText("Table Number");

        //TextField passwordField = new TextField();
        //usernameField.setPromptText("Password");

        Button loginButton = new Button("Login");
       // loginButton.setOnAction(e -> menuHandler.handleLogin(tableNumberField.getText()));

        //grid.add(usernameField, 0, 0);
        //grid.add(passwordField, 0, 1);
        //grid.add(loginButton, 0, 1);

        loginBox.getChildren().addAll(tableNumberField, loginButton);
        return loginBox;
    }
}
