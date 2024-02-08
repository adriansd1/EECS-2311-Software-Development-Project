package com.example.eecs2311termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientSide extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Initialize Page Title
        stage.setTitle("Sushi Buffet Menu");

        //Initialize Border Pane
        BorderPane borderPane = new BorderPane();

        //Initialize new Menu Bar
        MenuBar menuBar = new MenuBar();

        //Menu Bar for kitchen side
        MenuBar kitchenBar = new MenuBar();

        //Menu for kitchen
        Menu kitchenMenu = new Menu("Kitchen");
        kitchenMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        kitchenBar.getMenus().addAll(kitchenMenu);

        //Kitchen Options
        MenuItem kitchenTicketsMenu = new MenuItem("Tickets");
        kitchenTicketsMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        MenuItem kitchenShiftsMenu = new MenuItem("Clock-In/Out");
        kitchenShiftsMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        kitchenMenu.getItems().addAll(kitchenTicketsMenu, kitchenShiftsMenu);

        //Set height of Menu Bars
        menuBar.setStyle("-fx-pref-height:  100;");
        //kitchenMenu.setStyle("-fx-pref-height: 100;");

        //Menu Initializations with styles
        Menu sushiMenu = new Menu("Sushi");
        sushiMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        Menu noodleMenu = new Menu("Noodles");
        noodleMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        Menu riceMenu = new Menu("Rice");
        riceMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        Menu dessertMenu = new Menu("Dessert");
        dessertMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");
        Menu drinkMenu = new Menu("Drinks");
        drinkMenu.setStyle("-fx-pref-height:  100; -fx-font-size: 30px;");

        //Initialize Sushi menu options
        MenuItem makiItem = new MenuItem("Maki");
        MenuItem nigiriItem = new MenuItem("Nigiri");
        MenuItem handrollItem = new MenuItem("Handroll");
        MenuItem sashimiItem = new MenuItem("Sashimi");

        //Initialize Noodle menu options
        MenuItem ramenItem = new MenuItem("Ramen");
        MenuItem udonItem = new MenuItem("Udon");
        MenuItem sobaItem = new MenuItem("Soba");
        MenuItem somenItem = new MenuItem("Somen");

        //Initialize Rice menu options
        MenuItem curryItem = new MenuItem("Curry Rice");
        MenuItem onigiriItem = new MenuItem("Onigiri");
        MenuItem omuriceItem = new MenuItem("Omurice");
        MenuItem donburiItem = new MenuItem("Donburi");

        //Initialize Drink menu options
        MenuItem sodaItem = new MenuItem("Soda/Pop");
        MenuItem juiceItem = new MenuItem("Juice");
        MenuItem sakeItem = new MenuItem("Sake");
        MenuItem beerItem = new MenuItem("Beer");

        //Adding Sushi options
        sushiMenu.getItems().addAll(makiItem, nigiriItem, handrollItem, sashimiItem);
        //Adding Noodle options
        noodleMenu.getItems().addAll(ramenItem, udonItem, sobaItem, somenItem);
        //Adding Rice options
        riceMenu.getItems().addAll(curryItem, onigiriItem, omuriceItem, donburiItem);
        //Adding Drink options
        drinkMenu.getItems().addAll(sodaItem, juiceItem, sakeItem, beerItem);


        menuBar.getMenus().addAll(sushiMenu, noodleMenu, riceMenu, dessertMenu, drinkMenu);

        Region spacer = new Region();
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        HBox menuHBox = new HBox(menuBar, spacer, kitchenBar);

        borderPane.setTop(menuHBox);

        Scene scene = new Scene(borderPane, 640, 480);
        //Scene scene = new Scene(borderPane, 320, 240);
        stage.setScene(scene);

        MenuHandler menuHandler = new MenuHandler();

        makiItem.setOnAction(menuHandler::handleSushi);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}