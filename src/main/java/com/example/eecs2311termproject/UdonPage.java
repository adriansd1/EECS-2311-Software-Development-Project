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
 * @Description GUI and Business Logic implemented to receive orders from the Udon Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class UdonPage extends Page{
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage udonStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Udon Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        //Udon Food items for menu
        Food beefUdon = new Food("Beef Udon", 8.50,  680, 19.0, 38.0, 16.0);
        Food curryUdon = new Food("Curry Udon", 7.75, 720, 14.13, 37.0, 19.2);
        Food tempuraUdon = new Food("Tempura Udon", 7.65, 570, 22.0, 42.0, 8.8);

        //Added items to food menu
        FoodMenu udonMenu = new FoodMenu();
        udonMenu.addFoods(beefUdon);
        udonMenu.addFoods(curryUdon);
        udonMenu.addFoods(tempuraUdon);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane beefSquare = createFoodSquare(beefUdon.getName(), beefUdon.getPrice());
        StackPane currySquare = createFoodSquare(curryUdon.getName(), curryUdon.getPrice());
        StackPane tempuraSquare = createFoodSquare(tempuraUdon.getName(), tempuraUdon.getPrice());

        //Adding foods to HBox
        foodItems.getChildren().addAll(beefSquare, currySquare, tempuraSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            udonStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        udonStage.setScene(scene);
        udonStage.show();
    }
}
