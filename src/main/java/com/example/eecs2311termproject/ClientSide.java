package com.example.eecs2311termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author samda
 * @Description: This class contains the client side of the application and contains the GUI
 * and paths to various menus for the user to choose from
 */
public class ClientSide extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Initialize Page Title
        stage.setTitle("Edrick Sushi Buffet");

        //Software Icon
        Image icon = new Image(getClass().getResourceAsStream("/com/example/eecs2311termproject/Images/pngaaa.com-4077801.png"));
        stage.getIcons().add(icon);

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

        //Add all menus to menu bar
        menuBar.getMenus().addAll(sushiMenu, noodleMenu, riceMenu, dessertMenu, drinkMenu);

        //Add spacer so user items are on left while kitchen side is on the right
        Region spacer = new Region();
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        HBox menuHBox = new HBox(menuBar, spacer, kitchenBar);

        //Setting stage and layout
        borderPane.setTop(menuHBox);
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setScene(scene);

        //On button clicks
        MenuHandler menuHandler = new MenuHandler();
        makiItem.setOnAction(menuHandler::handleMaki);
        nigiriItem.setOnAction(menuHandler::handleNigiri);
        handrollItem.setOnAction(menuHandler::handleHandroll);
        sashimiItem.setOnAction(menuHandler::handleSashimi);

        //Welcome message
        VBox welcomeMessage = new VBox();
        Label welcomeLabel = new Label("Welcome to ");
        Label restaurantName = new Label("Edrick Sushi!");
        Label italicText = new Label("Please explore our menu.");
        Label historyLabel1 = new Label("Our restaurant has been serving  delicious sushi dishes for over a decade,");
        Label historyLabel2 = new Label("combining traditional Japanese flavors with innovative recipes.");
        Label ownerMessage = new Label("A message from the owner: We're thrilled to have you dine with us and experience the exquisite taste of our handcrafted sushi rolls. Enjoy your meal!");

        //Styles and formatting for welcome message
        welcomeLabel.setStyle("-fx-font-size: 20px;");
        restaurantName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        italicText.setStyle("-fx-font-size: 20px; -fx-font-style: italic;");
        historyLabel1.setPadding(new Insets(5, 0, 0 ,0));
        historyLabel1.setStyle("-fx-font-size: 16px;");
        historyLabel2.setStyle("-fx-font-size: 16px;");
        ownerMessage.setStyle("-fx-font-size: 16px; -fx-font-style: italic;");
        ownerMessage.setPadding(new Insets(5, 0, 0 ,0));

        welcomeMessage.getChildren().addAll(welcomeLabel, restaurantName, italicText, historyLabel1, historyLabel2, ownerMessage);
        welcomeMessage.setAlignment(Pos.CENTER);
        borderPane.setCenter(welcomeMessage);

        //Show output
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}