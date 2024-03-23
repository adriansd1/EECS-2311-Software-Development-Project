package com.example.eecs2311termproject;

public class PaymentHandler {

    public static double calculateTotalPrice(Order order) {
        // Access the Order's totalPrice directly
        // return order.getPrice();
        return 0.0; // To prevent errors
    }

    public static double calculateTotalPriceWithTip(Order order, double tipPercentage) {
        double totalPrice = calculateTotalPrice(order);
        double tipAmount = totalPrice * (tipPercentage / 100);
        return totalPrice + tipAmount;
    }

    public static boolean processPayment(Order order, double amount, double tipPercentage) {
        double totalPrice = calculateTotalPriceWithTip(order, tipPercentage);
        // Check if the provided amount is sufficient
        if (amount >= totalPrice) {
            // Perform the payment processing logic
            System.out.println("Payment successful! Total Amount: $" + totalPrice);
            // Optionally, update the order status to "Paid"
            order.updateStatus("Paid");
            return true;
        } else {
            System.out.println("Insufficient funds. Payment failed.");
            return false;
        }
    }
}
