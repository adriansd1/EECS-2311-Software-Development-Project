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

public class ViewOrder {
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage orderStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button confirmOrderButton = new Button("Confirm Order");

        //Title for menu
        Label titleLabel = new Label("Your Order");
        //ADD WITHIN LABEL TEXTVIEW TO ENTER TABLE NUMBER

        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //TEST VALUES MUST BE DELETED
        //
        //
        Food beer = new Food("Beer. 5%", 5.70,  260, 5.0, 35.0, 0.0);
        Food sake = new Food("Sake. 37%", 2.20, 70, 0.6, 2.0, 0.0);
        Food margarita = new Food("Maragrita. 8%", 7.65, 380, 12.0, 61.0, 3.5);
        //
        //
        //DELETE

        //VBox to hold the squares containing the foods
        VBox orderItems = new VBox(10);
        orderItems.setAlignment(Pos.TOP_LEFT);
        orderItems.setPadding(new Insets(10));
        orderItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane beerSquare = createFoodSquare(beer.getName(), beer.getPrice());
        StackPane sakeSquare = createFoodSquare(sake.getName(), sake.getPrice());
        StackPane margSquare = createFoodSquare(margarita.getName(), margarita.getPrice());

        //Adding foods to VBox
        orderItems.getChildren().addAll(beerSquare, sakeSquare, margSquare);

        //On action to close menu when pressing home button
        confirmOrderButton.setOnAction(e -> {
            orderStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, confirmOrderButton, orderItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        orderStage.setScene(scene);
        orderStage.show();
    }

    //Method to create squares to hold food items
    private static StackPane createFoodSquare(String name, double price) {
        //Style for square
        Rectangle square = new Rectangle(250, 50);
        square.setFill(Color.LIGHTGRAY);
        square.setStroke(Color.BLACK);

        //Labels for name and price of food
        Label nameLabel = new Label(name);
        Label priceLabel = new Label("$" + price);

        //Item quantity field
        TextField itemQuantity = new TextField("1");
        itemQuantity.setMaxWidth(40);
        itemQuantity.setAlignment(Pos.CENTER);

        //Add buttons and text field to control box
        HBox quantityControls = new HBox(5);
        quantityControls.setAlignment(Pos.CENTER);
        quantityControls.getChildren().addAll(itemQuantity);

        //VBox to hold square and add button now
        HBox squareContent = new HBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, priceLabel, quantityControls);

        //Stack pane to hold all previous items
        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        return squarePane;
    }
}
