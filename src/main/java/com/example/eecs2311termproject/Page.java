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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;


public abstract class Page {
    private static Map<String, Boolean> foodInfoDisplayed = new HashMap<>();


    //Method to create square panes for food
    protected static StackPane createFoodSquare(String name, double price, String imagePath) {
        //Style for square
        Rectangle square = new Rectangle(250, 400);
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

            Food f = new Food(name, price);//, quantity);


            boolean found = false;

            for (Food foodItem : ViewOrder.currentOrder.getFoodOrder()) {
                if (foodItem.getName().equals(f.getName()) && foodItem.getPrice() == f.getPrice()) {

                    foodItem.setQuantity(quantity);
                    //ClientSide.clientOrder.setRunningTotal(quantity * price);
                    found = true;

                    //PostgreSQL.updateQuantity(f.getName(), quantity);

                    break;
                }
            }

            if (!found) {
                f.setQuantity(quantity);
                ViewOrder.currentOrder.addFood(f);
                //ClientSide.clientOrder.setRunningTotal(quantity * price);
                //System.out.println(ClientSide.clientOrder.getRunningTotal());

                //PostgreSQL.WriteToDatabase(f.getName(), f.getPrice(), quantity);


            }
        });

        //VBox to hold square and add button now
        VBox squareContent = new VBox(5);
        squareContent.setAlignment(Pos.CENTER);
        squareContent.getChildren().addAll(nameLabel, priceLabel, quantityControls, addButton);

        // Info button to display detailed information about the food
        Button infoButton = new Button("Info");
        infoButton.setOnAction(e -> displayFoodInfo(name, squareContent));

        // Load the image
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);

        // Add info button to squareContent
        squareContent.getChildren().add(infoButton);

        // Add the image view to the content
        squareContent.getChildren().add(imageView);



        //Stack pane to hold all previous items
        StackPane squarePane = new StackPane();
        squarePane.getChildren().addAll(square, squareContent);

        return squarePane;
    }




    private static void displayFoodInfo(String foodName, VBox squareContent) {
        // If info for this food is already displayed, hide it
        if (foodInfoDisplayed.containsKey(foodName) && foodInfoDisplayed.get(foodName)) {
            squareContent.getChildren().removeIf(node -> node instanceof VBox);
            foodInfoDisplayed.put(foodName, false);
            return;
        }

        // Otherwise, display info for this food
        Food food = getFoodDetails(foodName);
        if (food != null) {
            VBox layout = new VBox(10);
            layout.setAlignment(Pos.TOP_CENTER);

            Label nameLabel = new Label("Name: " + food.getName());
            Label caloriesLabel = new Label("Calories: " + food.getCalories());
            Label proteinLabel = new Label("Protein: " + food.getProtein());
            Label carbsLabel = new Label("Carbs: " + food.getCarbs());
            Label fatLabel = new Label("Fat: " + food.getFat());

            layout.getChildren().addAll(nameLabel, caloriesLabel, proteinLabel, carbsLabel, fatLabel);

            // Set initial position above the scene
            layout.setTranslateY(-200);

            // Create translate transition to slide the bar down
            TranslateTransition slideDown = new TranslateTransition(Duration.seconds(0.5), layout);
            slideDown.setToY(0); // Set final position

            // Play the animation
            slideDown.play();

            // Add the food info layout to the square content VBox
            squareContent.getChildren().add(layout);

            // Update info display status for this food
            foodInfoDisplayed.put(foodName, true);
        }
    }




    private static Food getFoodDetails(String foodName) {
        // Mocking food details based on the food name
        switch (foodName) {
            case "Curry Rice":
                return new Food("Curry Rice", 10.70, 680, 23.0, 67.0, 14.0);
            case "Onigiri":
                return new Food("Onigiri", 2.20, 190, 4.13, 23.0, 5.2);
            case "Omurice":
                return new Food("Omurice", 6.65, 470, 12.0, 67.0, 21.0);
            case "Donburi":
                return new Food("Donburi", 9.75, 675, 14.0, 63.0, 19.0);
            case "Salmon Sashimi":
                return new Food("Salmon Sashimi", 10.70, 680, 23.0, 67.0, 14.0);
            case "Tuna Sashimi":
                return new Food("Tuna Sashimi", 2.20, 190, 4.13, 23.0, 5.2);
            case "Octopus Sashimi":
                return new Food("Octopus Sashimi", 6.65, 470, 12.0, 67.0, 21.0);
            case "Beef Udon":
                return new Food("Beef Udon", 9.75, 675, 14.0, 63.0, 19.0);
            case "Curry Udon":
                return new Food("Curry Udon", 10.70, 680, 23.0, 67.0, 14.0);
            case "Tempura Udon":
                return new Food("Tempura Udon", 2.20, 190, 4.13, 23.0, 5.2);
            case "Beef Ramen":
                return new Food("Beef Ramen", 6.65, 470, 12.0, 67.0, 21.0);
            case "Miso Ramen":
                return new Food("Miso Ramen", 9.75, 675, 14.0, 63.0, 19.0);
            case "Chicken Ramen":
                return new Food("Chicken Ramen", 10.70, 680, 23.0, 67.0, 14.0);
            case "Tuna Nigiri":
                return new Food("Tuna Nigiri", 2.20, 190, 4.13, 23.0, 5.2);
            case "Salmon Nigiri":
                return new Food("Salmon Nigiri", 6.65, 470, 12.0, 67.0, 21.0);
            case "Yellowtail Nigiri":
                return new Food("Yellowtail Nigiri", 9.75, 675, 14.0, 63.0, 19.0);
            case "Tuna Roll":
                return new Food("Tuna Roll", 10.70, 680, 23.0, 67.0, 14.0);
            case "Salmon Maki":
                return new Food("Salmon Maki", 2.20, 190, 4.13, 23.0, 5.2);
            case "California Roll":
                return new Food("California Roll", 6.65, 470, 12.0, 67.0, 21.0);
            case "Tuna Hand Roll":
                return new Food("Tuna Hand Roll", 9.75, 675, 14.0, 63.0, 19.0);
            case "Salmon Hand Roll":
                return new Food("Salmon Hand Roll", 10.70, 680, 23.0, 67.0, 14.0);
            case "Eel Hand Roll":
                return new Food("Eel Hand Roll", 2.20, 190, 4.13, 23.0, 5.2);
            case "Beer":
                return new Food("Beer", 2.20, 190, 4.13, 23.0, 5.2);
            case "Sake":
                return new Food("Sake", 6.65, 470, 12.0, 67.0, 21.0);
            case "Maragrita":
                return new Food("Maragrita", 9.75, 675, 14.0, 63.0, 19.0);
            case "Ice Cream":
                return new Food("Ice Cream", 10.70, 680, 23.0, 67.0, 14.0);
            case "Cookie":
                return new Food("Cookie", 10.70, 680, 23.0, 67.0, 14.0);
            case "Brownie":
                return new Food("Brownie", 2.20, 190, 4.13, 23.0, 5.2);
            case "Jello":
                return new Food("Jello", 2.20, 190, 4.13, 23.0, 5.2);
            case "Coke":
                return new Food("Coke", 6.65, 470, 12.0, 67.0, 21.0);
            case "Sprite":
                return new Food("Sprite", 9.75, 675, 14.0, 63.0, 19.0);
            case "Fanta":
                return new Food("Fanta", 10.70, 680, 23.0, 67.0, 14.0);
            case "Water":
                return new Food("Water", 2.20, 190, 4.13, 23.0, 5.2);
            case "Orange Juice":
                return new Food("Orange Juice", 6.65, 470, 12.0, 67.0, 21.0);
            default:
                return null; // Return null for unknown food names
        }
    }

}
