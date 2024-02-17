package com.example.eecs2311termproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author samda
 * @Description GUI and Business Logic implemented to receive orders from the Rice Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class RicePage extends Page {
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage riceStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Rice Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //Rice Food items for menu
        Food curryRice = new Food("Curry Rice", 10.70,  680, 23.0, 67.0, 14.0);
        Food onigiriRice = new Food("Onigiri", 2.20, 190, 4.13, 23.0, 5.2);
        Food omuRice = new Food("Omurice", 6.65, 470, 12.0, 67.0, 21.0);
        Food donburiRice = new Food("Donburi", 9.75, 675, 14.0, 63.0, 19.0);

        //Added items to food menu
        FoodMenu riceMenu = new FoodMenu();
        riceMenu.addFoods(curryRice);
        riceMenu.addFoods(onigiriRice);
        riceMenu.addFoods(omuRice);
        riceMenu.addFoods(donburiRice);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane currySquare = createFoodSquare(curryRice.getName(), curryRice.getPrice());
        StackPane onigiriSquare = createFoodSquare(onigiriRice.getName(), onigiriRice.getPrice());
        StackPane omuSquare = createFoodSquare(omuRice.getName(), omuRice.getPrice());
        StackPane donSquare = createFoodSquare(donburiRice.getName(), donburiRice.getPrice());

        //Adding foods to HBox
        foodItems.getChildren().addAll(currySquare, onigiriSquare, omuSquare, donSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            riceStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        riceStage.setScene(scene);
        riceStage.show();
    }
}
