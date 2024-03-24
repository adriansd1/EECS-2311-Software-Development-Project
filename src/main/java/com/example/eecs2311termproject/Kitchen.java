package com.example.eecs2311termproject;
import java.util.ArrayList;
//Why does this extend class, needs GUI follow client side to define functionality
public class Kitchen {
    ArrayList<String> order;

    public ArrayList<String> sendOrder() {
        return order;
    }

    public void recieveOrder(ArrayList<String> orders) {
        this.order = orders;
    }

    public void cookrice(){
        System.out.print("Cooking rice");
        System.out.print("Rice ready!");
    }
    public void slicefish(){
        System.out.print("Slicing fish");
        System.out.print("Fish Ready!");

    }
    public void handleSpecialRequests(Order order) {
        String specialRequests = order.getSpecialRequests();
        if (!specialRequests.isEmpty()) {
            System.out.println("Handling special requests for order: " + order);
            // Your implementation to handle each special request
            }
        else{
            System.out.println("No special requests for order: " + order);
        }
    }
}





