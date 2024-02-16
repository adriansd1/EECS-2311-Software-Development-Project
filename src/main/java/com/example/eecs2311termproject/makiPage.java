package com.example.eecs2311termproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class makiPage {
    public static void display() {
        Stage makiStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        Button homeButton = new Button("Home");

        Label titleLabel = new Label("Maki Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //Maki Food items for menu
        Food tunaMaki = new Food("Tuna Roll", 0.50, 26, 1.17, 4.92, 0.07);
        Food salmonMaki = new Food("Salmon Maki", 0.50, 28, 1.12, 4.92, 0.23);
        Food californiaRoll = new Food("California Roll", 0.60, 33, 1.38, 6.31, 0.12);


        FoodMenu makiMenu = new FoodMenu();
        makiMenu.addFoods(tunaMaki);
        makiMenu.addFoods(salmonMaki);
        makiMenu.addFoods(californiaRoll);

        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        StackPane tunaSquare = createFoodSquare(tunaMaki.getName(), tunaMaki.getPrice());
        StackPane salmonSquare = createFoodSquare(salmonMaki.getName(), salmonMaki.getPrice());
        StackPane caliSquare = createFoodSquare(californiaRoll.getName(), californiaRoll.getPrice());

        foodItems.getChildren().addAll(tunaSquare, salmonSquare, caliSquare);

        homeButton.setOnAction(e -> {
            makiStage.close();
        });

        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        makiStage.setScene(scene);
        makiStage.show();
    }


    private static StackPane createFoodSquare(String name, double price) {
        Rectangle square = new Rectangle(250, 250);
        square.setFill(Color.LIGHTGRAY);
        square.setStroke(Color.BLACK);

        Label nameLabel = new Label(name);
        Label priceLabel = new Label("$" + price);

        TextField itemQuantity = new TextField("1");
        itemQuantity.setMaxWidth(40);
        itemQuantity.setAlignment(Pos.CENTER);

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
            itemQuantity.setText(String.valueOf(quantity + 1));
        });

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
            if(quantity >= 1) {
                itemQuantity.setText(String.valueOf(quantity - 1));
            }
        });

        HBox quantityControls = new HBox(5);
        quantityControls.setAlignment(Pos.CENTER);
        quantityControls.getChildren().addAll(minusButton, itemQuantity, plusButton);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
        });

        VBox squareContent = new VBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, priceLabel, quantityControls, addButton);

        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        return squarePane;
    }
}
