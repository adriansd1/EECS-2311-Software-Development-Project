package com.example.eecs2311termproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class FoodTest {

    private Food food;

    @BeforeEach
    void setUp() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Ingredient1");
        ingredients.add("Ingredient2");

        ArrayList<String> restrictions = new ArrayList<>();
        restrictions.add("Restriction1");
        restrictions.add("Restriction2");

        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        food = new Food("Test Food", 10.0, 200, 15.5, 20.0, 5.0, ingredients, restrictions, allergies);
        ArrayList<String> returnedIngredients = food.getIngredients();
    }

    @Test
    void testGetCalories() {
        assertEquals(200, food.getCalories());
    }

    @Test
    void testGetProtein() {
        assertEquals(15.5, food.getProtein());
    }

    @Test
    void testGetCarbs() {
        assertEquals(20.0, food.getCarbs());
    }

    @Test
    void testGetFat() {
        assertEquals(5.0, food.getFat());
    }

    @Test
    void testGetIngredients() {
        assertEquals(2, food.getIngredients().size());
        assertTrue(food.getIngredients().contains("Ingredient1"));
        assertTrue(food.getIngredients().contains("Ingredient2"));
    }



    @Test
    void testToString() {
        assertEquals("Food Name: Test Food Price: 10.0 Quantity: 0", food.toString());
    }

    @Test
    void testGetDietaryRestrictions() {
        assertEquals(2, food.getDietaryRestrictions().size());
        assertTrue(food.getDietaryRestrictions().contains("Restriction1"));
        assertTrue(food.getDietaryRestrictions().contains("Restriction2"));
    }

    @Test
    void testGetAllergies() {
        assertEquals(2, food.getAllergies().size());
        assertTrue(food.getAllergies().contains("Allergy1"));
        assertTrue(food.getAllergies().contains("Allergy2"));
    }

    @Test
    void testGetName() {
        assertEquals("Test Food", food.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(10.0, food.getPrice());
    }

    @Test
    void testSetQuantity() {
        food.setQuantity(5);
        assertEquals(5, food.quantity);
    }

}
