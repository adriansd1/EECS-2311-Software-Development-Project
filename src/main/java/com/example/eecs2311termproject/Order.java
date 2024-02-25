package com.example.eecs2311termproject;
import java.util.*;


public class Order {
    protected double runningTotal = 0.0;
    protected int tableNumber;
    protected boolean orderCompleted;
    protected ArrayList<Food> foodOrder;

    Order(int tableNumber){
        this.tableNumber = tableNumber;
        this.foodOrder = new ArrayList<>();
    }

    public void addFood(Food f){
        this.foodOrder.add(f);
        //runningTotal += f.getPrice();
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

    public String getStringOfFoods(){
        StringBuilder foodString = new StringBuilder();
        for (Food food : foodOrder) {
            foodString.append(food.foodName).append("\n");
        }
        return foodString.toString();
    }




    // the above is temporary/simple implementation for now
    //unsure about below






    //Unused
	private String orderID;
    //Causing errors private Customer customer;
	private String status;
    //Causing errors private ArrayList<OrderItem> items;
    private Date orderTime;
    private double totalPrice;
    private String specialRequests;
    private String promotionsApplied;

    //Causes errors makes project unrunnable
    /*public Order(String orderID, Customer customer, ArrayList<OrderItem> items) {
    	this.orderID = orderID;
    	this.customer = new Customer();
        this.items = new ArrayList<>();
        this.status = "Pending";
        this.orderTime = new Date(); // sets current time as order time
        this.totalPrice = calculateTotalPrice(); //update totalPrice by recalculating it using the calculateTotalPrice() method
        this.specialRequests = "";
        this.promotionsApplied = "";
    }
    
    public ArrayList<OrderItem> getOrder() {
    	return items; 
    }
    
    public void setOrder(ArrayList<OrderItem> items) {
    	this.items = items;
        totalPrice = calculateTotalPrice(); //after updating the list of items, recalculates the totalprice
    }
    */
    
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









    //Code unused
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

    /*
    public void addOrder(OrderItem item) {
    	if (checkItemAvailability(item)) {
	    	items.add(item);
	    	System.out.println("Added" + item.name + "to your order!");
	    	//totalPrice = totalPrice + item.getPrice(); 
            totalPrice = calculateTotalPrice();
    	}
    	else {
	    	System.out.println("Sorry," + item.name + "isn't available right now.");
    	}
    }
    
    public void removeOrder(OrderItem item) {
    	items.remove(item);
    	System.out.println("Removed" + item.name + "from your order!");
        totalPrice = calculateTotalPrice(); //recalculating
    }
    */
    public void cancelOrder() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for confirmation
        System.out.println("Are you sure you want to cancel your order? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        // Check the user's response
        if ("yes".equals(response)) {
            // Logic to cancel the order
            this.totalPrice = 0;
           //Errors this.items = new ArrayList<>();
            this.status = "Cancelled";
            System.out.println("Your order has been cancelled.");
        } 
        
        else {
            System.out.println("Order cancellation aborted.");
        }
    }
    public boolean processPayment(double amount) {
        // Check if the provided amount is sufficient
        if (amount >= totalPrice) {
            // Perform the payment processing logic
            System.out.println("Payment successful! Total Amount: $" + totalPrice);
            // Optionally, update the order status to "Paid"
            updateStatus("Paid");
            return true;
        } else {
            System.out.println("Insufficient funds. Payment failed.");
            return false;
        }
    }

    private double calculateTotalPrice() {
       //Errors return items.stream().mapToDouble(OrderItem::getPrice).sum();
        return 0.0; //temp to prevent errors
    }
    
    @Override
    public String toString() {
        // Implement the toString method to provide a meaningful representation of the Order
        StringBuilder t = new StringBuilder();
        for(Food f: ClientSide.clientOrder.getFoodOrder()){
            if(!t.toString().contains(f.toString()))
            t.append(f.toString());
            t.append("\n");
        }
        return t.toString();
    }

}
