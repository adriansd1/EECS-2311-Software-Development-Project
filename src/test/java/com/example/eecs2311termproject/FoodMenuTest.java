package com.example.eecs2311termproject;

import com.example.eecs2311termproject.Food;
import com.example.eecs2311termproject.FoodMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FoodMenuTest {
    private FoodMenu foodMenu;
    private Food sushi;
    private Food noodles;
    private Food rice;

    @BeforeEach
    void setUp() {
        foodMenu = new FoodMenu();
        sushi = new Food("Sushi", 10.99);
        noodles = new Food("Noodles", 8.99);
        rice = new Food("Rice", 6.99);
    }

    @Test
    void testAddFoods() {
        foodMenu.addFoods(sushi);
        assertEquals(1, foodMenu.getMenuFoods().size());
        assertTrue(foodMenu.getMenuFoods().contains(sushi));
    }

    @Test
    void testRemoveFoods() {
        foodMenu.addFoods(sushi);
        foodMenu.addFoods(noodles);
        foodMenu.removeFoods(sushi);
        assertEquals(1, foodMenu.getMenuFoods().size());
        assertFalse(foodMenu.getMenuFoods().contains(sushi));
        assertTrue(foodMenu.getMenuFoods().contains(noodles));
    }

    @Test
    void testGetFood() {
        foodMenu.addFoods(sushi);
        foodMenu.addFoods(noodles);
        foodMenu.addFoods(rice);
        assertEquals(sushi, foodMenu.getFood(0));
        assertEquals(noodles, foodMenu.getFood(1));
        assertEquals(rice, foodMenu.getFood(2));
    }

    @Test
    void testGetMenuFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(sushi);
        foods.add(noodles);
        foods.add(rice);
        foodMenu.setMenuFoods(foods);
        assertEquals(foods, foodMenu.getMenuFoods());
    }
}
