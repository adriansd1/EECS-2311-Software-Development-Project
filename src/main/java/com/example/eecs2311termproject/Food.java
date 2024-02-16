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
    protected ArrayList<String> ingredients;
    protected ArrayList<String> dietaryRestrictions;
    protected ArrayList<String> allergies;

    // Constructor
    public Food(String foodName, int calories, double protein, double carbs, double fat,
                ArrayList<String> ingredients, ArrayList<String> dietaryRestrictions,
                ArrayList<String> allergies) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.allergies = allergies;
        this.foodName = foodName;
    }

    public Food(String foodName, double price, int calories, double protein, double carbs, double fat){
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.ingredients = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();
        this.allergies = new ArrayList<>();
        this.foodName = foodName;
        this.price = price;
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

    public static ArrayList<String> getIngredients() {
       // return ingredients;
        return null;
    }


    public ArrayList<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public String getName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }
}
