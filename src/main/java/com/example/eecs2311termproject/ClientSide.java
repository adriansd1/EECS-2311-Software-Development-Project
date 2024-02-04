package com.example.eecs2311termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientSide extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientSide.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Sushi Buffet Menu");

        BorderPane borderPane = new BorderPane();

        Label headerLabel = new Label("Header");

        borderPane.setTop(headerLabel);

        MenuBar menuBar = new MenuBar();

        menuBar.setStyle("-fx-pref-height:  100;");

        Menu fileMenu = new Menu("Menu");

        MenuItem sushiItem = new MenuItem("Sushi");
        MenuItem drinkItem = new MenuItem("Drinks");
        MenuItem exitItem = new MenuItem("Exit");

        fileMenu.getItems().addAll(sushiItem, drinkItem, exitItem);

        menuBar.getMenus().add(fileMenu);

        borderPane.setTop(menuBar);

        Scene scene = new Scene(borderPane, 320, 240);
        stage.setScene(scene);

        MenuHandler menuHandler = new MenuHandler();

        sushiItem.setOnAction(menuHandler::handleSushi);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}