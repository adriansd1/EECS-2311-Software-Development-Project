package com.example.eecs2311termproject;

import java.util.ArrayList;

/**
 * @author Avery Backus
 * @Description Food Class to create food instances
 */
public class Food {
    protected String foodName;
    protected double price;
    protected int calories;
    protected double protein;
    protected double carbs;
    protected double fat;

    protected int quantity = 0;


    public Food(){
    }

    public Food(String foodName, double price, int calories, double protein, double carbs, double fat){
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.foodName = foodName;
        this.price = price;
    }

    public Food(String foodName, double price){
        this.foodName = foodName;
        this.price = price;
    }

    public Food(String foodName, double price, int quantity) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter methods
    public int getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    @Override
    public String toString() {
        return "Food Name: " + foodName + " Price: " + price + " Quantity: " + quantity;
    }

    public String getName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }

    //MIGHT BE ISSUE
    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

}
