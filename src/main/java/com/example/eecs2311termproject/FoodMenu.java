package com.example.eecs2311termproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoodMenu {
    ArrayList<Food> menuFoods;

    public FoodMenu(){
        menuFoods = new ArrayList<>();
    }

    public FoodMenu(ArrayList<Food> foods){}

    public void setMenuFoods(ArrayList<Food> menuFoods) {
        this.menuFoods = menuFoods;
    }

    public ArrayList<Food> getMenuFoods() {
        return menuFoods;
    }

    public void addFoods(Food food){
        this.menuFoods.add(food);
    }

    public void removeFoods(Food food){
        this.menuFoods.remove(food);
    }

    public Food getFood(int index){
        return menuFoods.get(index);
    }

}
