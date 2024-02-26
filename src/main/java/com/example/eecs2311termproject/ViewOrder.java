package com.example.eecs2311termproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewOrder {
    //Set-up and display GUI
    public static Order currentOrder;
    public static void display() {
        //Setting stage and container
        Stage orderStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Setting current order
        //currentOrder.getFoodOrder().addAll(ClientSide.clientOrder.getFoodOrder());

        //Home button to close menu
        Button confirmOrderButton = new Button("Confirm Order");

        //Title for menu
        Label titleLabel = new Label("Your Order");
        //ADD WITHIN LABEL TEXTVIEW TO ENTER TABLE NUMBER

        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        //VBox to hold the squares containing the foods
        VBox orderItems = new VBox(10);
        orderItems.setAlignment(Pos.TOP_LEFT);
        orderItems.setPadding(new Insets(10));
        orderItems.setAlignment(Pos.CENTER);


        for (int i = 1; i<=PostgreSQL.getRowCount(); i++) {
            //Squares containing foods and prices
            StackPane foodSquare = createFoodSquare(PostgreSQL.readFoodNameFromDatabase(i), PostgreSQL.readPriceFromDatabase(i), PostgreSQL.readQuantityFromDatabase(i), orderItems);
            //Adding foods to VBox
            orderItems.getChildren().add(foodSquare);
        }


        //On action to close menu when pressing home button
        confirmOrderButton.setOnAction(e -> {
            orderItems.getChildren().clear();
            //TicketsPage.currentTicket.getFoodOrder().addAll(currentOrder.getFoodOrder());
           // currentOrder.getFoodOrder().clear();
            ClientSide.clientOrder.getFoodOrder().clear();
            orderStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, confirmOrderButton, orderItems);

        // Wrap the layout in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);

        //Make scene(scrollable)
        Scene scene = new Scene(scrollPane, 400, 300);
        //Set and show scene
        orderStage.setScene(scene);
        orderStage.show();
    }

    //Method to create squares to hold food items
    private static StackPane createFoodSquare(String name, double price, int quantity, VBox itemContainer) {
        //Style for square
        Rectangle square = new Rectangle(250, 50);
        square.setFill(Color.AZURE);
        square.setStroke(Color.BLACK);

        //Labels for name and price of food
        Label nameLabel = new Label(name);
        Label priceLabel = new Label("$" + price);

        //Button to remove food item
        Button removeItemButton = new Button("x");



        //Item quantity field MAKE THIS LABEL
        TextField itemQuantity = new TextField(String.valueOf(quantity));
        itemQuantity.setMaxWidth(40);
        itemQuantity.setAlignment(Pos.CENTER);

        //Add buttons and text field to control box
        HBox quantityControls = new HBox(5);
        quantityControls.setAlignment(Pos.CENTER);
        quantityControls.getChildren().addAll(itemQuantity);

        //VBox to hold square and add button now
        HBox squareContent = new HBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, priceLabel, quantityControls, removeItemButton);

        //Stack pane to hold all previous items
        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        removeItemButton.setOnAction(e -> {
            // Get the index of the StackPane containing the square
            int index = itemContainer.getChildren().indexOf(squarePane);
            // Remove the StackPane containing the square from the VBox
            itemContainer.getChildren().remove(index);
            PostgreSQL.deleteFood(name);
        });

        return squarePane;
    }
}
