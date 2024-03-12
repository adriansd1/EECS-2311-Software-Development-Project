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
public class makiPage extends Page{
    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage makiStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);

        //Home button to close menu
        Button homeButton = new Button("Home");

        //Title for menu
        Label titleLabel = new Label("Maki Menu");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;"); // -fx-alignment: top-center;");

        //Maki Food items for menu
        Food tunaMaki = new Food("Tuna Roll", 0.50, 26, 1.17, 4.92, 0.07);
        Food salmonMaki = new Food("Salmon Maki", 0.50, 28, 1.12, 4.92, 0.23);
        Food californiaRoll = new Food("California Roll", 0.60, 33, 1.38, 6.31, 0.12);

        String imagePathFortunaMaki = "com/example/eecs2311termproject/Images/tuna roll.jpeg";
        String imagePathForsalmonMaki = "com/example/eecs2311termproject/Images/salmon maki.jpg";
        String imagePathForcaliforniaRoll  = "com/example/eecs2311termproject/Images/california roll.jpeg";

        //Added items to food menu
        FoodMenu makiMenu = new FoodMenu();
        makiMenu.addFoods(tunaMaki);
        makiMenu.addFoods(salmonMaki);
        makiMenu.addFoods(californiaRoll);

        //HBox to hold the squares containing the foods
        HBox foodItems = new HBox(10);
        foodItems.setAlignment(Pos.TOP_LEFT);
        foodItems.setPadding(new Insets(10));
        foodItems.setAlignment(Pos.CENTER);

        //Squares containing foods and prices
        StackPane tunaSquare = createFoodSquare(tunaMaki.getName(), tunaMaki.getPrice(), imagePathFortunaMaki);
        StackPane salmonSquare = createFoodSquare(salmonMaki.getName(), salmonMaki.getPrice(), imagePathForsalmonMaki);
        StackPane caliSquare = createFoodSquare(californiaRoll.getName(), californiaRoll.getPrice(), imagePathForcaliforniaRoll);

        //Adding foods to HBox
        foodItems.getChildren().addAll(tunaSquare, salmonSquare, caliSquare);

        //On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            makiStage.close();
        });

        //Add title, homeButton and menu options to scene
        layout.getChildren().addAll(titleLabel, homeButton, foodItems);
        Scene scene = new Scene(layout, 400, 300);
        //Set and show scene
        makiStage.setScene(scene);
        makiStage.show();
    }
}
