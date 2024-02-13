package com.example.eecs2311termproject;

import javafx.event.ActionEvent;

public class MenuHandler {

    public void handleMaki(ActionEvent event) {
        System.out.println("Maki Button");
        FoodMenu makiMenu = new FoodMenu();
        makiPage.display();

    }

    public void handleNigiri(ActionEvent event) {
        System.out.println("Nigiri Button");
        FoodMenu nigiriMenu = new FoodMenu();
    }

    public void handleHandroll(ActionEvent event) {
        System.out.println("Handroll Button");
        FoodMenu handrollMenu = new FoodMenu();
    }

    public void handleSashimi(ActionEvent event) {
        System.out.println("Sashimi Button");
        FoodMenu sashimiMenu = new FoodMenu();
    }
}