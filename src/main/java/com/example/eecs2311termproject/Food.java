package com.example.eecs2311termproject;

import java.util.ArrayList;

public class Food {
    protected int calories;
    protected double protein;
    protected double carbs;
    protected double fat;
    protected ArrayList<String> ingredients;
    protected ArrayList<String> dietaryRestrictions;
    protected ArrayList<String> allergies;

    // Constructor
    public Food(int calories, double protein, double carbs, double fat,
                ArrayList<String> ingredients, ArrayList<String> dietaryRestrictions,
                ArrayList<String> allergies) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.allergies = allergies;
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
        return ingredients;
    }

    public ArrayList<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

}
