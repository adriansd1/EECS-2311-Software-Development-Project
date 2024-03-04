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

public class TicketsPage {

    public static Order currentTicket;

    protected static StackPane createTicketSquare(String foodName, int quantity, HBox foodItems) {
        //Style for square
        Rectangle square = new Rectangle(250, 250);
        square.setFill(Color.LIGHTGRAY);
        square.setStroke(Color.BLACK);


        //Labels for order's table number
        Label nameLabel = new Label("Table Number: 1");
        Label orderItemsLabel = new Label("Items: " + foodName);
        Label orderItemQuantityLabel = new Label("Quantity: " + quantity);




        //Button to complete order
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> {
            foodItems.getChildren().remove(square.getParent());
            PostgreSQL.deleteFood(foodName);
        });

        //VBox to hold square and complete button now
        VBox squareContent = new VBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, orderItemsLabel, orderItemQuantityLabel, completeButton);

        //Stack pane to hold all previous items
        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        return squarePane;
    }

    public static void display() {
        //Setting stage and container
        Stage ticketStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Current Orders");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");


        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        for (int i = 1; i<=PostgreSQL.getRowCount(); i++) {
            //Squares containing foods and quantity
            String name = PostgreSQL.readFoodNameFromDatabase(i);
            int quantity = PostgreSQL.readQuantityFromDatabase(i);
            StackPane foodSquare = createTicketSquare(name, quantity, foodItems);
            //Adding foods to VBox
            foodItems.getChildren().add(foodSquare);
        }

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            ticketStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);

        // Wrap the layout in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 400, 300);

        //Set and show scene
        ticketStage.setScene(scene);
        ticketStage.show();
    }




}
