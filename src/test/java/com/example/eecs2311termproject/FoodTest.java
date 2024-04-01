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

    @Test
    public void testGetCalories() {
        Food food = new Food("Apple", 1.5, 100, 0.5, 25, 0.3);
        assertEquals(100, food.getCalories());
    }

    @Test
    public void testGetProtein() {
        Food food = new Food("Chicken Breast", 5.0, 150, 25, 0, 3.5);
        assertEquals(25, food.getProtein());
    }

    @Test
    public void testGetCarbs() {
        Food food = new Food("Rice", 2.0, 130, 2.5, 30, 0.5);
        assertEquals(30, food.getCarbs());
    }

    @Test
    public void testGetFat() {
        Food food = new Food("Olive Oil", 3.0, 120, 0, 0, 14);
        assertEquals(14, food.getFat());
    }

    @Test
    public void testToString() {
        Food food = new Food("Banana", 0.5, 105, 1.3, 27, 0.4);
        assertEquals("Food Name: Banana Price: 0.5 Quantity: 0", food.toString());
    }

    @Test
    public void testGetName() {
        Food food = new Food("Milk", 2.0, 150);
        assertEquals("Milk", food.getName());
    }

    @Test
    public void testGetPrice() {
        Food food = new Food("Bread", 1.0);
        assertEquals(1.0, food.getPrice());
    }

    @Test
    public void testSetQuantity() {
        Food food = new Food("Orange", 0.75);
        food.setQuantity(5);
        assertEquals(5, food.quantity);
    }

}
