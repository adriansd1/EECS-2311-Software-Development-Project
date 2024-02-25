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


    public void handleTickets(ActionEvent event){
        System.out.println("Ticket Button");
        TicketsPage.display();
    }
    //Handling Ramen menu option
    public void handleRamen(ActionEvent event) {
        //Print line for debugging
        System.out.println("Ramen Button");
        RamenPage.display();
    }

    //Handling Udon menu option
    public void handleUdon(ActionEvent event) {
        //Print line for debugging
        System.out.println("Udon Button");
        UdonPage.display();
    }

    //Handling Rice Menu option
    public void handleRice(ActionEvent actionEvent) {
        //Print line for debugging
        System.out.println("Rice Button");
        RicePage.display();
    }

    //Handling Alcohol menu options
    public void handleAlc(ActionEvent actionEvent) {
        //Print line for debugging
        System.out.println("Alc Button");
        AlcPage.display();
    }

    //Handling Drink menu options
    public void handleDrink(ActionEvent actionEvent) {
        //Print line for debugging
        System.out.println("Alc Button");
        DrinkPage.display();
    }

    //Handling Dessert menu options
    public void handleDessert(ActionEvent actionEvent) {
        //Print line for debugging
        System.out.println("Dessert Button");
        DessertPage.display();
    }

    //Handling View Order button
    public void handleViewOrder(ActionEvent actionEvent) {
        //Print line for debugging
        System.out.println("View Order button");
        System.out.println(ClientSide.clientOrder.toString());
        ViewOrder.display();
    }
}