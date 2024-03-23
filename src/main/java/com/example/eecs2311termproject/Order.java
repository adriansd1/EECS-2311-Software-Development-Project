package com.example.eecs2311termproject;
import java.util.*;


public class Order {
    protected double runningTotal = 0.0;
    protected int tableNumber;
    protected boolean orderCompleted;
    protected ArrayList<Food> foodOrder;

    public Order(){
        tableNumber = 1;
        foodOrder = new ArrayList<>();
    }

    Order(int tableNumber){
        this.tableNumber = tableNumber;
        this.foodOrder = new ArrayList<>();
    }

    public void addFood(Food f){
        this.foodOrder.add(f);
        runningTotal += f.getPrice();
    }

    public void addFood(ArrayList<Food> foods){
        if (foods != null) {
            for (Food f : foods) {
                foodOrder.add(f);
                runningTotal += f.getPrice();
            }
        }
    }

    public void removeFood(Food foodToRemove) {
        if (foodOrder.remove(foodToRemove)) {
            runningTotal -= foodToRemove.getPrice();
        }
    }

    public void setRunningTotal(double runningTotal){
        this.runningTotal += runningTotal;
    }
    public double getRunningTotal() {
        return this.runningTotal;
    }

    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }
    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setOrderCompleted(boolean orderCompleted) {
        this.orderCompleted = orderCompleted;
    }

    public boolean isOrderCompleted() {
        return this.orderCompleted;
    }

    public ArrayList<Food> getFoodOrder() {
        return this.foodOrder;
    }

    protected double tipPercentage;

    public double getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(double tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public String getStringOfFoods(){
        StringBuilder foodString = new StringBuilder();
        for (Food food : foodOrder) {
            foodString.append(food.foodName).append("\n");
        }
        return foodString.toString();
    }




    // the above is temporary/simple implementation for now
    //unsure about below






    //Unused, apply later.
	protected String orderID;
    protected String status;
    protected Date orderTime;
    protected double totalPrice;
    protected String specialRequests;
    protected String promotionsApplied;


    public String getStatus() {
    	return status;
    }
    
    public void updateStatus(String status) {
    	this.status = status;
    }
    
    public String getOrderID() {
    	return orderID;
    }
    
    public void setOrderID(String orderID) {
    	this.orderID = orderID;
    }

    //Code unused, will apply later.
    public String getSpecialRequests() {
    	return specialRequests;
    }
    
    public void setSpecialRequests(String specialRequests) {
    	this.specialRequests = specialRequests;
    }
    
    public String getPromotionsApplied() {
    	return promotionsApplied;
    }
    
    public void setPromotionsApplied(String promotionsApplied) {
    	this.promotionsApplied = promotionsApplied;
    }


    public void cancelOrder() {
            this.runningTotal = 0;
            foodOrder = new ArrayList<>();
        }

    public boolean processPayment(double amount) {
        // Check if the provided amount is sufficient
        if (amount >= runningTotal) {
            // Perform the payment processing logic
            System.out.println("Payment successful! Total Amount: $" + runningTotal);
            // Optionally, update the order status to "Paid"
            updateStatus("Paid");
            return true;
        } else {
            System.out.println("Insufficient funds. Payment failed.");
            return false;
        }
    }


    // Override toString() method in Order class
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Food food : foodOrder) {
            stringBuilder.append(food.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

}
