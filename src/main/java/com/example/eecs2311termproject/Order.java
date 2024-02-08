package com.example.eecs2311termproject;
import java.util.*;

//The class is currently an abstract one since I am not currently able to implement specific methods yet.
public abstract class Order { 
	private String orderID;
	private Customer customer;
	private String status;
    private ArrayList<OrderItem> items; 
    private Date orderTime;
    private double totalPrice;
    private String specialRequests;
    private String promotionsApplied;

    
    public Order(String orderID, Customer customer, ArrayList<OrderItem> items) {
    	this.orderID = orderID;
    	this.customer = new Customer();
        this.items = new ArrayList<>();
        this.status = "Pending";
        this.orderTime = new Date(); // sets current time as order time
        this.totalPrice = 0.0;
        this.specialRequests = "";
        this.promotionsApplied = "";
    }
    
    public ArrayList<OrderItem> getOrder() {
    	return items; 
    }
    
    public void setOrder(ArrayList<OrderItem> items) {
    	this.items = items;
    }
    
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

    public void addOrder(OrderItem item) {
    	if (checkItemAvailability(item)) {
	    	items.add(item);
	    	System.out.println("Added" + item.name + "to your order!");
	    	totalPrice = totalPrice + item.getPrice(); 
    	}
    	else {
	    	System.out.println("Sorry," + item.name + "isn't available right now.");
    	}
    }
    
    public void removeOrder(OrderItem item) {
    	items.remove(item);
    	System.out.println("Removed" + item.name + "from your order!");
    }
    
    public void cancelOrder() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for confirmation
        System.out.println("Are you sure you want to cancel your order? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        // Check the user's response
        if ("yes".equals(response)) {
            // Logic to cancel the order
            this.totalPrice = 0;
            this.items = new ArrayList<>();
            this.status = "Cancelled";
            System.out.println("Your order has been cancelled.");
        } 
        
        else {
            System.out.println("Order cancellation aborted.");
        }
    }
    
    public abstract boolean checkItemAvailability(OrderItem item); // Work on this later
    
    public abstract void applyPromotions(String promotionsApplied);  // Work on this later
    
    public double getPrice() {
    	return totalPrice;
    }
    
    @Override
    public String toString() {
    	return "";
    }

}

/* Currently using these classes in order to prevent compiling errors. 
I will remove them once I receive the other classes. */
class Customer {
	private String customerName;
}

abstract class OrderItem {
	String name;
	double price;
	
	public abstract double getPrice();
	
}
