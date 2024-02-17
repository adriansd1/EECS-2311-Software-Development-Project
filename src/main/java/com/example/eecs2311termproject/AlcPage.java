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
 * @Description GUI and Business Logic implemented to receive orders from the Alcohol Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class AlcPage extends Page {
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage alcStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Alcohol Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //Drink items for menu
        Food beer = new Food("Beer. 5%", 5.70,  260, 5.0, 35.0, 0.0);
        Food sake = new Food("Sake. 37%", 2.20, 70, 0.6, 2.0, 0.0);
        Food margarita = new Food("Maragrita. 8%", 7.65, 380, 12.0, 61.0, 3.5);

        //Added items to food menu
        FoodMenu alcMenu = new FoodMenu();
        alcMenu.addFoods(beer);
        alcMenu.addFoods(sake);
        alcMenu.addFoods(margarita);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane beerSquare = createFoodSquare(beer.getName(), beer.getPrice());
        StackPane sakeSquare = createFoodSquare(sake.getName(), sake.getPrice());
        StackPane margSquare = createFoodSquare(margarita.getName(), margarita.getPrice());

        //Adding foods to HBox
        foodItems.getChildren().addAll(beerSquare, sakeSquare, margSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            alcStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        alcStage.setScene(scene);
        alcStage.show();
    }

}
