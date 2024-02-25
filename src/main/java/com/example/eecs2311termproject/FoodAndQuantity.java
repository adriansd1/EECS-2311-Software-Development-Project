package com.example.eecs2311termproject;

/**
 * @author samda
 * @Description: Encapsulation for food class to include the quantity of each food
 * which will make storing orders and displaying GUI much more seamless
 */
public class FoodAndQuantity {

    public Food food = new Food();

    public int quantity = 0;


    public FoodAndQuantity(Food food, int quantity){
        this.food = food;
        this.quantity = quantity;
    }

    public Food getJustFood(){
        return food;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setJustFood(Food f){
        food = f;
    }

    public void setQuantity(int q){
        quantity += q;
    }

}
