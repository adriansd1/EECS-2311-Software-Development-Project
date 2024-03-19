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

/**
 * @author samda
 * @Description GUI and Business Logic implemented to receive orders from the Nigiri Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class NigiriPage extends Page {
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage nigiriStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Nigiri Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        //Nigiri Food items for menu
        Food tunaNigiri = new Food("Tuna Nigiri", 0.40, 46, 1.94, 9.16, 0.12);
        Food salmonNigiri = new Food("Salmon Nigiri", 0.40, 48, 1.85, 9.0, 0.36);
        Food yellowtailNigiri = new Food("Yellowtail Nigiri", 0.60, 54, 3.0, 8.0, 1.0);

        // Image paths for each food item
        String imagePathForTuna = "com/example/eecs2311termproject/Images/tuna nigiri.jpeg";
        String imagePathForSalmon = "com/example/eecs2311termproject/Images/salmon nigiri.jpg";
        String imagePathForYellowtail = "com/example/eecs2311termproject/Images/yellowtail nigiri.jpg";

        //Added items to food menu
        FoodMenu nigiriMenu = new FoodMenu();
        nigiriMenu.addFoods(tunaNigiri);
        nigiriMenu.addFoods(salmonNigiri);
        nigiriMenu.addFoods(yellowtailNigiri);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane tunaSquare = createFoodSquare(tunaNigiri.getName(), tunaNigiri.getPrice(), imagePathForTuna);
        StackPane salmonSquare = createFoodSquare(salmonNigiri.getName(), salmonNigiri.getPrice(), imagePathForSalmon);
        StackPane yellowTailSquare = createFoodSquare(yellowtailNigiri.getName(), yellowtailNigiri.getPrice(), imagePathForYellowtail);

        //Adding foods to HBox
        foodItems.getChildren().addAll(tunaSquare, salmonSquare, yellowTailSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            nigiriStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        nigiriStage.setScene(scene);
        nigiriStage.show();
    }
}
