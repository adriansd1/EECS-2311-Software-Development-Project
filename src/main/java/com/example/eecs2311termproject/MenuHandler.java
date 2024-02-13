package com.example.eecs2311termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuHandler {

    public void handleMaki(ActionEvent event) {
        System.out.println("Maki Button");
        FoodMenus makiMenu = new FoodMenus();
        //stage.setScene(makiScene);

    }

    public void handleNigiri(ActionEvent event) {
        System.out.println("Nigiri Button");
        FoodMenus nigiriMenu = new FoodMenus();
    }

    public void handleHandroll(ActionEvent event) {
        System.out.println("Handroll Button");
        FoodMenus handrollMenu = new FoodMenus();
    }

    public void handleSashimi(ActionEvent event) {
        System.out.println("Sashimi Button");
        FoodMenus sashimiMenu = new FoodMenus();
    }
}