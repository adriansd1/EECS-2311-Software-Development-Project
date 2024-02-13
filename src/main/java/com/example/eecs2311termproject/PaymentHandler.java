package com.example.eecs2311termproject;

public class PaymentHandler {
    public static double calculateTotalPrice(Order order) {
        //Access the Order's totalPrice directly
        // return order.getPrice();
        return 0.0; //To prevent errors
    }

    public static boolean processPayment(Order order, double amount) {
        double totalPrice = calculateTotalPrice(order);
        //Check if the provided amount is sufficient
        if (amount >= totalPrice) {
            //Perform the payment processing logic
            System.out.println("Payment successful! Total Amount: $" + totalPrice);
            //Optionally, update the order status to "Paid"
            order.updateStatus("Paid");
            return true;
        } else {
            System.out.println("Insufficient funds. Payment failed.");
            return false;
        }
    }
}

