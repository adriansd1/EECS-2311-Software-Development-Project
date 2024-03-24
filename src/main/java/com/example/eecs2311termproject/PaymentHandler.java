package com.example.eecs2311termproject;

public class PaymentHandler {
    public static double calculateTotalPrice(Order order) {
        return order.getRunningTotal();
    }


    public static boolean validatePaymentMethod(PaymentDetails paymentDetails) {
        return PostgreSQL.validatePaymentMethod(paymentDetails.getPaymentMethod(), paymentDetails.getIdentifier());
    }


    public static boolean detectFraud(Order order) {
        // Example: Check if the order size is unusually large
        if (order.getFoodOrder().size() > 100) { // Arbitrary condition
            System.out.println("Potential fraud detected. Order size is unusually large.");
            return true;
        }
        return false;
    }

    public static boolean processPayment(Order order, PaymentDetails paymentDetails) {
        boolean paymentSuccess = false;
        String paymentStatusMessage;

        if (!validatePaymentMethod(paymentDetails)) {
            paymentStatusMessage = "Payment failed: Invalid payment method.";
        } else if (detectFraud(order)) {
            paymentStatusMessage = "Payment failed: Fraud detected.";
        } else {
            double totalPrice = calculateTotalPrice(order);
            paymentStatusMessage = "Processing payment for order total: $" + totalPrice;
            paymentSuccess = true; // Simulating payment success
            order.updateStatus("Paid");
        }

        // Log the payment attempt regardless of success
        PostgreSQL.logPaymentTransaction(order.getOrderID(), paymentDetails.getPaymentMethod(), paymentDetails.getIdentifier(), order.getRunningTotal(), paymentSuccess);

        // Display the payment status message
        System.out.println(paymentStatusMessage);
        if (paymentSuccess) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed!");
        }

        return paymentSuccess;
    }
}
