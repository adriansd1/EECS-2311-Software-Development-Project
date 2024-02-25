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

public abstract class Page {

    //Method to create square panes for food
    protected static StackPane createFoodSquare(String name, double price) {
        //Style for square
        Rectangle square = new Rectangle(250, 250);
        square.setFill(Color.LIGHTGRAY);
        square.setStroke(Color.BLACK);

        //Labels for name and price of food
        Label nameLabel = new Label(name);
        Label priceLabel = new Label("$" + price);

        //Item quantity field
        TextField itemQuantity = new TextField("1");
        itemQuantity.setMaxWidth(40);
        itemQuantity.setAlignment(Pos.CENTER);

        //Plus button to increase number of given food item
        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
            itemQuantity.setText(String.valueOf(quantity + 1));
        });

        //Minus button to decrease number of given food item
        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
            if(quantity >= 1) {
                itemQuantity.setText(String.valueOf(quantity - 1));
            }
        });

        //Add buttons and text field to control box
        HBox quantityControls = new HBox(5);
        quantityControls.setAlignment(Pos.CENTER);
        quantityControls.getChildren().addAll(minusButton, itemQuantity, plusButton);

        //Button to add food
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
            Food f = new Food(name, price);
            if(ClientSide.clientOrder.getFoodOrder().contains(f)) {
                ClientSide.clientOrder.getFoodOrder().get(ClientSide.clientOrder.getFoodOrder().indexOf(f)).setQuantity(quantity);
            }
            else {
                f.setQuantity(quantity);
                ClientSide.clientOrder.addFood(f);
                ClientSide.clientOrder.setRunningTotal(quantity * price);
                System.out.println(ClientSide.clientOrder.getRunningTotal());
            }


        });

        //VBox to hold square and add button now
        VBox squareContent = new VBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, priceLabel, quantityControls, addButton);

        //Stack pane to hold all previous items
        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        return squarePane;
    }
}
