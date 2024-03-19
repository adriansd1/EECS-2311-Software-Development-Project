package com.example.eecs2311termproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.InputStream;



class OrderTest {
    private Order order;
    private Food californiaRoll;
    private Food salmonNigiri;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;


    @BeforeEach
    void setUp() {
        order = new Order(1); // Order allocated to table 1

        // Example nutritional info and ingredients for a California Roll
        int calories = 255;
        double protein = 9.0;
        double carbs = 38.0;
        double fat = 7.0;
        double price = 8.0;
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList("Rice", "Cucumber", "Crab meat", "Avocado", "Seaweed"));
        ArrayList<String> dietaryRestrictions = new ArrayList<>(Arrays.asList("Gluten-Free"));
        ArrayList<String> allergies = new ArrayList<>(Arrays.asList("Shellfish"));

        californiaRoll = new Food("California Roll", price, calories, protein, carbs, fat, ingredients, dietaryRestrictions, allergies);

        double priceSalmonNigiri = 5.0; // Assuming the price for Salmon Nigiri
        int caloriesSalmonNigiri = 70; // Approximate calories for one piece of Salmon Nigiri
        double proteinSalmonNigiri = 5.0; // Grams of protein per piece
        double carbsSalmonNigiri = 9.0; // Grams of carbs per piece
        double fatSalmonNigiri = 1.0; // Grams of fat per piece
        ArrayList<String> ingredientsSalmonNigiri = new ArrayList<>(Arrays.asList("Rice", "Salmon"));
        ArrayList<String> dietaryRestrictionsSalmonNigiri = new ArrayList<>(); // Assuming none specified
        ArrayList<String> allergiesSalmonNigiri = new ArrayList<>(Arrays.asList("Fish")); // Allergy warning for fish

        salmonNigiri = new Food("Salmon Nigiri", priceSalmonNigiri, caloriesSalmonNigiri, proteinSalmonNigiri, carbsSalmonNigiri, fatSalmonNigiri, ingredientsSalmonNigiri, dietaryRestrictionsSalmonNigiri, allergiesSalmonNigiri);

    }

    @Test
    void addSingleFoodCorrectlyIncreasesTotal() {
        order.addFood(californiaRoll);
        assertEquals(8.0, order.getRunningTotal(), "Adding a California Roll should update the running total correctly.");
    }

    @Test
    void addMultipleFoodCorrectlyIncreasesTotal() {
        ArrayList<Food> foods = new ArrayList<Food>();
        foods.add(californiaRoll);
        foods.add(salmonNigiri);
        order.addFood(foods);
        assertEquals(13.0, order.getRunningTotal(), "Adding list of foods should update running total correctly.");
    }
    @Test
    void removeFoodCorrectlyDecreasesTotal(){
        order.addFood(salmonNigiri);
        order.addFood(californiaRoll);
        order.removeFood(californiaRoll);
        assertEquals(5.0, order.getRunningTotal(), "Removing a food item should decrease the running total correctly.");
    }

    @Test
    void setOrderCompletedUpdatesStatusCorrectly() {
        order.setOrderCompleted(true);
        assertTrue(order.isOrderCompleted(), "Setting order to completed should update the status correctly.");
    }

    @Test
    void addingFoodUpdatesFoodListCorrectly() {
        ArrayList<Food> foods = new ArrayList<Food>();
        foods.add(californiaRoll);
        foods.add(salmonNigiri);
        order.addFood(foods);
        assertTrue(order.getFoodOrder().contains(californiaRoll), "Adding food should update the food list correctly.");
    }

    @Test
    void processPaymentSuccessfully() {
        order.setRunningTotal(20.0);
        double paymentAmount = 20.0;
        boolean result = order.processPayment(paymentAmount);
        assertTrue(result, "Payment should be processed successfully.");
        assertEquals("Paid", order.getStatus(), "Order status should be updated to 'Paid'.");
    }

    @Test
    void processPaymentUnsuccessfully() {
        order.setRunningTotal(25.0);
        double paymentAmount = 20.0;
        boolean result = order.processPayment(paymentAmount);
        assertFalse(result, "Payment should not be processed due to insufficient funds.");
        assertNotEquals("Paid", order.getStatus(), "Order status should not be 'Paid' after unsuccessful payment.");
    }

    @Test
    void cancelOrder() {
        order.addFood(new Food("Test Food", 10.0)); // Ensure this Food has a non-zero price
        double initialTotalPrice = order.getRunningTotal(); // Assuming getRunningTotal() returns totalPrice
        order.cancelOrder();

        assertTrue(initialTotalPrice > 0, "Initial total price should be greater than 0.");
        assertEquals(0, order.getRunningTotal(), "Total price should be reset to 0 after cancellation.");
        assertTrue(order.getFoodOrder().isEmpty(), "Order list should be empty.");
    }


}