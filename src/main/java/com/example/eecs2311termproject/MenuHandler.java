package com.example.eecs2311termproject;

import javafx.event.ActionEvent;

/**
 * @author samda
 * @Desciption Class to handle all button clicks from the client side
 * Potentially in the second iteration use this to handle kitchen side buttons
 */
public class MenuHandler {

    //Handling Maki menu option
    public void handleMaki(ActionEvent event) {
        //Print line for debugging
        System.out.println("Maki Button");
        //Create Maki menu page
        makiPage.display();

    }

    //Handling Nigiri menu option
    public void handleNigiri(ActionEvent event) {
        //Print line for debugging
        System.out.println("Nigiri Button");
        NigiriPage.display();
    }

    //Handling Handroll menu option
    public void handleHandroll(ActionEvent event) {
        //Print line for debugging
        System.out.println("Handroll Button");
        HandRollPage.display();
    }

    //Handling Sashimi menu option
    public void handleSashimi(ActionEvent event) {
        //Print line for debugging
        System.out.println("Sashimi Button");
        SashimiPage.display();
    }
}