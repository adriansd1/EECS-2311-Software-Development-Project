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
    @Test
public void testAddFood() {
    Order order = new Order();
    Food food1 = new Food("Pizza", 10.0);
    order.addFood(food1);
    assertEquals(1, order.getFoodOrder().size());
}

    @Test
    public void testAddMultipleFoods() {
        Order order = new Order();
        Food food1 = new Food("Burger", 5.0);
        Food food2 = new Food("Fries", 3.0);
        Food food3 = new Food("Soda", 1.5);
        order.addFood(food1);
        order.addFood(food2);
        order.addFood(food3);
        assertEquals(3, order.getFoodOrder().size());
    }

    @Test
    public void testEmptyOrder() {
        Order order = new Order();
        assertTrue(order.getFoodOrder().isEmpty());
    }

    @Test
    public void testRemoveFood() {
        Order order = new Order();
        Food food1 = new Food("Salad", 4.0);
        Food food2 = new Food("Soup", 3.0);
        order.addFood(food1);
        order.addFood(food2);
        order.getFoodOrder().remove(food1);
        assertEquals(1, order.getFoodOrder().size());
    }

    @Test
    public void testOrderContainsFood() {
        Order order = new Order();
        Food food1 = new Food("Ice Cream", 2.5);
        order.addFood(food1);
        assertTrue(order.getFoodOrder().contains(food1));
    }

    @Test
    public void testOrderDoesNotContainFood() {
        Order order = new Order();
        Food food1 = new Food("Sandwich", 4.0);
        Food food2 = new Food("Pasta", 7.0);
        order.addFood(food1);
        assertFalse(order.getFoodOrder().contains(food2));
    }

    @Test
    public void testAddNullFood() {
        Order order = new Order();
        order.addFood(null);
        assertTrue(order.getFoodOrder().contains(null));
    }

    @Test
    public void testAddDuplicateFood() {
        Order order = new Order();
        Food food1 = new Food("Steak", 15.0);
        order.addFood(food1);
        order.addFood(food1);
        assertEquals(2, order.getFoodOrder().size());
    }

    @Test
    public void testGetFoodOrder() {
        Order order = new Order();
        ArrayList<Food> foodList = new ArrayList<>();
        Food food1 = new Food("Chicken", 8.0);
        Food food2 = new Food("Rice", 2.0);
        foodList.add(food1);
        foodList.add(food2);
        order.addFood(food1);
        order.addFood(food2);
        assertEquals(foodList, order.getFoodOrder());
    }


}

