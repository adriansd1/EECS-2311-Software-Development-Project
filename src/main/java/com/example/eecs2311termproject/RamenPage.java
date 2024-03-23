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
 * @Description GUI and Business Logic implemented to receive orders from the Ramen Menu and
 * add them to the users order so that they choose quantities of different items which will be
 * added to their order
 */
public class RamenPage extends Page {
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage ramenStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Ramen Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        //Ramen Food items for menu
        Food beefRamen = new Food("Beef Ramen", 8.70,  680, 17.0, 57.0, 14.0);
        Food misoRamen = new Food("Miso Ramen", 5.20, 199, 14.13, 23.0, 5.2);
        Food chickenRamen = new Food("Chicken Ramen", 7.65, 570, 12.0, 81.0, 21.0);

        String imagePathForbeefRamen = "/com/example/eecs2311termproject/Images/beef ramen.jpg";
        String imagePathForchickenRamen = "/com/example/eecs2311termproject/Images/chicken ramen.jpg";
        String imagePathFormisoRamen = "/com/example/eecs2311termproject/Images/miso ramen.jpg";

        //Added items to food menu
        FoodMenu ramenMenu = new FoodMenu();
        ramenMenu.addFoods(beefRamen);
        ramenMenu.addFoods(misoRamen);
        ramenMenu.addFoods(chickenRamen);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane beefSquare = createFoodSquare(beefRamen.getName(), beefRamen.getPrice(), imagePathForbeefRamen);
        StackPane misoSquare = createFoodSquare(misoRamen.getName(), misoRamen.getPrice(), imagePathFormisoRamen);
        StackPane chickenSquare = createFoodSquare(chickenRamen.getName(), chickenRamen.getPrice(), imagePathForchickenRamen);

        //Adding foods to HBox
        foodItems.getChildren().addAll(beefSquare, misoSquare, chickenSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            ramenStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        ramenStage.setScene(scene);
        ramenStage.show();
    }

}
