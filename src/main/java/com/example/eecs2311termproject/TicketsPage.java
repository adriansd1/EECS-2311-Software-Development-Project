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

public class TicketsPage {

    protected static StackPane createTicketSquare(Order order, HBox foodItems) {
        //Style for square
        Rectangle square = new Rectangle(250, 250);
        square.setFill(Color.LIGHTGRAY);
        square.setStroke(Color.BLACK);


        //Labels for order's table number
        Label nameLabel = new Label("Table Number: " + String.valueOf(order.getTableNumber()));
        Label orderItemsLabel = new Label("Items: " + order.getStringOfFoods());




        //Button to complete order
        Button completeButton = new Button("Complete");
        completeButton.setOnAction(e -> {
            order.orderCompleted = true;
            foodItems.getChildren().remove(square.getParent());
            order.getFoodOrder().clear();
        });

        //VBox to hold square and complete button now
        VBox squareContent = new VBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, orderItemsLabel, completeButton);

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
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");



        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        for(Food f: ClientSide.clientOrder.getFoodOrder()){
            //Squares containing foods and prices
            StackPane foodSquare = createTicketSquare(ViewOrder.currentOrder, foodItems);
            //Adding foods to VBox
            foodItems.getChildren().add(foodSquare);
        }


        //Squares containing foods and prices
        //StackPane orderSquare = createTicketSquare(tempOrder);

        //for(Food f: ViewOrder.currentOrder.getFoodOrder()){
            StackPane orderSquare = createTicketSquare(ViewOrder.currentOrder, foodItems);
        //}


        //Adding foods to HBox
        //foodItems.getChildren().addAll(foodSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            ticketStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        ticketStage.setScene(scene);
        ticketStage.show();
    }




}
