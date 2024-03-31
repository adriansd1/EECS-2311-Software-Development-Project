package com.example.eecs2311termproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class BuffetPage {
    protected static Stage diningTypeStage;
    protected static StackPane buffetPane(){
        Rectangle square = new Rectangle(250, 160);
        square.setFill(Color.AZURE);
        square.setStroke(Color.BLACK);

        Label buffetLabel = new Label("Select the number of guests");
        Label selectTableLabel = new Label("Select you table number");
        Label priceLabel = new Label("$30 per person");

        // Create a ComboBox for table numbers
        ComboBox<Integer> tableNumberComboBox = new ComboBox<>();
        tableNumberComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        tableNumberComboBox.setValue(1); // Set default value
        tableNumberComboBox.setPromptText("Select table number");

        //textfield
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

        //Button to confirm number of people
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            int quantity = Integer.parseInt(itemQuantity.getText());
            Food f = new Food("ALL YOU CAN EAT", 30);
            f.setQuantity(quantity);

            int tableNumber = tableNumberComboBox.getValue();

            ClientSide.buffetButton.setDisable(true);
            diningTypeStage.close();
            PostgreSQL.WriteToDatabase(f.getName(), f.getPrice(), quantity, tableNumber);
            PostgreSQL.WriteTableToDatabase(tableNumber, true);


        });



        //VBox to hold square and add button now
        VBox squareContent = new VBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(selectTableLabel, tableNumberComboBox, buffetLabel, priceLabel, quantityControls, okButton);

        //Stack pane to hold all previous items
        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        return squarePane;


    }


    public static void display(){
        //setting stage
        diningTypeStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        homeButton.setOnAction(e -> {
            diningTypeStage.close();
        });

        StackPane buffetSquare = buffetPane();


        //Title for menu
        Label titleLabel = new Label("All You Can Eat!");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");



        Label noteLabel = new Label("Once you confirm you cannot go back");
        Label ifNotLabel = new Label("If you do not want to eat buffet-style, return home");


        noteLabel.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");
        ifNotLabel.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");









        layout.getChildren().addAll(titleLabel, homeButton, buffetSquare, noteLabel, ifNotLabel);
        Scene scene = new Scene(layout, 400, 300);
        diningTypeStage.setScene(scene);
        diningTypeStage.show();



    }
}
