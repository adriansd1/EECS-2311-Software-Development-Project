package com.example.eecs2311termproject;
import java.util.*;


public class Order {
    protected double runningTotal = 0.0;
    //protected int tableNumber;
    protected boolean orderCompleted;
    protected ArrayList<Food> foodOrder;
    protected static boolean AYCE;

    public Order(){
        foodOrder = new ArrayList<>();
    }


    public void addFood(Food f){
        this.foodOrder.add(f);

    }



    public ArrayList<Food> getFoodOrder() {
        return this.foodOrder;
    }

    public String getStringOfFoods(){
        StringBuilder foodString = new StringBuilder();
        for (Food food : foodOrder) {
            foodString.append(food.foodName).append("\n");
        }
        return foodString.toString();
    }



    @Override
    public String toString() {
        // Implement the toString method to provide a meaningful representation of the Order
        StringBuilder t = new StringBuilder();
        for(Food f: ViewOrder.currentOrder.getFoodOrder()){
            if(!t.toString().contains(f.toString()))
                t.append(f.toString());
            t.append("\n");
        }
        return t.toString();
    }

}
