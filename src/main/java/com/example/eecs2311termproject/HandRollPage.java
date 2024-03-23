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

/**
 * @author samda
 * @Description GUI and Business Logic implemented to receive orders from the Maki Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class HandRollPage extends Page{
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage handRollStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Hand Roll Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        //Handroll Food items for menu
        Food tunaHandRoll = new Food("/Tuna Hand Roll", 1.20, 194, 24.0, 21, 0.8);
        Food salmonHandRoll = new Food("/Salmon Hand Roll", 1.20, 162, 10.0, 16.0, 6.3);
        Food eelHandRoll = new Food("/Eel Hand Roll", 1.60, 170, 7.0, 22.0, 6.0);

        String imagePathFortunaHandRoll = "com/example/eecs2311termproject/Images/tuna handroll.jpeg";
        String imagePathForsalmonHandRoll = "com/example/eecs2311termproject/Images/salmon handroll.jpeg";
        String imagePathForeelHandRoll = "com/example/eecs2311termproject/Images/eel handroll.jpeg";

        //Added items to food menu
        FoodMenu handRollMenu = new FoodMenu();
        handRollMenu.addFoods(tunaHandRoll);
        handRollMenu.addFoods(salmonHandRoll);
        handRollMenu.addFoods(eelHandRoll);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane tunaSquare = createFoodSquare(tunaHandRoll.getName(), tunaHandRoll.getPrice(), imagePathFortunaHandRoll);
        StackPane salmonSquare = createFoodSquare(salmonHandRoll.getName(), salmonHandRoll.getPrice(), imagePathForsalmonHandRoll);
        StackPane eelSquare = createFoodSquare(eelHandRoll.getName(), eelHandRoll.getPrice(), imagePathForeelHandRoll);

        //Adding foods to HBox
        foodItems.getChildren().addAll(tunaSquare, salmonSquare, eelSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            handRollStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        handRollStage.setScene(scene);
        handRollStage.show();
    }
}
