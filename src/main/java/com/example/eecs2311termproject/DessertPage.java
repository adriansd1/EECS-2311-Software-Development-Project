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
 * @Description GUI and Business Logic implemented to receive orders from the Dessert Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class DessertPage extends Page{
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage dessertStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Dessert Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //Dessert items for menu
        Food iceCream = new Food("Ice Cream", 1.50,  160, 4.3, 42.0, 4.5);
        Food cookie = new Food("Cookie", 0.75, 240, 3.2, 47.0, 8.0);
        Food brownie = new Food("Brownie", 1.00, 330, 4.4, 51.0, 12.2);
        Food jello = new Food("Jello", 0.25, 20, 0.5, 6.5, 0.0);

        //Added items to food menu
        FoodMenu dessertMenu = new FoodMenu();
        dessertMenu.addFoods(iceCream);
        dessertMenu.addFoods(cookie);
        dessertMenu.addFoods(brownie);
        dessertMenu.addFoods(jello);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane icSquare = createFoodSquare(iceCream.getName(), iceCream.getPrice());
        StackPane cookieSquare = createFoodSquare(cookie.getName(), cookie.getPrice());
        StackPane brownieSquare = createFoodSquare(brownie.getName(), brownie.getPrice());
        StackPane jelloSquare = createFoodSquare(jello.getName(), jello.getPrice());

        //Adding foods to HBox
        foodItems.getChildren().addAll(icSquare, cookieSquare, brownieSquare, jelloSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            dessertStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        dessertStage.setScene(scene);
        dessertStage.show();
    }
}
