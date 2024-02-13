package com.example.eecs2311termproject;

import java.util.ArrayList;

public class FoodMenus {
    ArrayList<Food> menuFoods;

    public FoodMenus(){
        menuFoods = new ArrayList<>();
    }

    public void addFoods(Food food){
        menuFoods.add(food);
    }

    public void removeFoods(Food food){
        menuFoods.remove(food);
    }

}
