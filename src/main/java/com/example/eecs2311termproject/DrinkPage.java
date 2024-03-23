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
 * @Description GUI and Business Logic implemented to receive orders from the Drink Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class DrinkPage extends Page {
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage drinkStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Drink Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        //Drink items for menu
        Food coke = new Food("Coke", 1.00,  160, 0.3, 42.0, 0.0);
        Food sprite = new Food("Sprite", 1.00, 140, 0.2, 42.0, 0.0);
        Food fanta = new Food("Fanta", 1.00, 130, 0.4, 41.0, 0.0);
        Food water = new Food("Water", 0.0, 0, 0.0, 0.0, 0.0);
        Food orangeJuice = new Food("Orange Juice", 1.2, 110, 0.5, 32.0, 0.0);

        String imagePathForCoke = "/com/example/eecs2311termproject/Images/coke.jpeg";
        String imagePathForsprite = "/com/example/eecs2311termproject/Images/sprite.jpeg";
        String imagePathForfanta = "/com/example/eecs2311termproject/Images/fanta.jpeg";
        String imagePathForOrangeJuice = "/com/example/eecs2311termproject/Images/orange juice.jpg";
        String imagePathForWater = "/com/example/eecs2311termproject/Images/water.jpg";

        //Added items to food menu
        FoodMenu drinkMenu = new FoodMenu();
        drinkMenu.addFoods(coke);
        drinkMenu.addFoods(sprite);
        drinkMenu.addFoods(fanta);
        drinkMenu.addFoods(water);
        drinkMenu.addFoods(orangeJuice);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane waterSquare = createFoodSquare(water.getName(), water.getPrice(), imagePathForWater);
        StackPane cokeSquare = createFoodSquare(coke.getName(), coke.getPrice(), imagePathForCoke);
        StackPane spriteSquare = createFoodSquare(sprite.getName(), sprite.getPrice(), imagePathForsprite);
        StackPane fantaSquare = createFoodSquare(fanta.getName(), fanta.getPrice(), imagePathForfanta);
        StackPane orangeSquare = createFoodSquare(orangeJuice.getName(), orangeJuice.getPrice(), imagePathForOrangeJuice);

        //Adding foods to HBox
        foodItems.getChildren().addAll(waterSquare, cokeSquare, spriteSquare, fantaSquare, orangeSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            drinkStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        drinkStage.setScene(scene);
        drinkStage.show();
    }
}
