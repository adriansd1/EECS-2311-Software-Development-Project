package com.example.eecs2311termproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SashimiPage extends Page{
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage sashimiStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Sashimi Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //Maki Food items for menu
        Food tunaSashimi = new Food("Tuna Sashimi", 0.20, 31, 6.63, 0.0, 0.27);
        Food salmonSashimi = new Food("Salmon Sashimi", 0.20, 41, 6.13, 0.0, 1.68);
        Food octopusSashimi = new Food("Octopus Sashimi", 0.65, 10, 2.0, 0.0, 0.0);

        //Added items to food menu
        FoodMenu sashimiMenu = new FoodMenu();
        sashimiMenu.addFoods(tunaSashimi);
        sashimiMenu.addFoods(salmonSashimi);
        sashimiMenu.addFoods(octopusSashimi);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane tunaSquare = createFoodSquare(tunaSashimi.getName(), tunaSashimi.getPrice());
        StackPane salmonSquare = createFoodSquare(salmonSashimi.getName(), salmonSashimi.getPrice());
        StackPane octSquare = createFoodSquare(octopusSashimi.getName(), octopusSashimi.getPrice());

        //Adding foods to HBox
        foodItems.getChildren().addAll(tunaSquare, salmonSquare, octSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            sashimiStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        sashimiStage.setScene(scene);
        sashimiStage.show();
    }
}
