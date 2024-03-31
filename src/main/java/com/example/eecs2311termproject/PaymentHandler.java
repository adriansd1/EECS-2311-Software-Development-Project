package com.example.eecs2311termproject;

public class PaymentHandler {
    public static double calculateTotalPrice(Order order) {
        double totalPrice = PostgreSQL.getTotal(order.getOrderID());
        return totalPrice;
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


    public static boolean processPayment(Order order, PaymentDetails paymentDetails, double tipPercentage) {
        // First, validate the payment method
        if (!validatePaymentMethod(paymentDetails)) {
            System.out.println("Payment failed: Invalid payment method.");
            logPayment(order, paymentDetails, 0, tipPercentage,false); // Assuming a 0 totalAmount for failed attempt
            return false;
        }

        // Next, detect potential fraud
        if (detectFraud(order)) {
            System.out.println("Payment failed: Fraud detected.");
            logPayment(order, paymentDetails, 0, tipPercentage, false); // Assuming a 0 totalAmount for failed attempt
            return false;
        }

        // Then calculate the total price including tip
        double baseTotalPrice = calculateTotalPrice(order);
        double tipAmount = baseTotalPrice * tipPercentage;
        double totalPrice = baseTotalPrice + tipAmount;

        // Attempt to subtract funds for the payment method
        boolean fundsSubtracted = PostgreSQL.subtractFunds(paymentDetails.getIdentifier(), totalPrice);
        if (!fundsSubtracted) {
            System.out.println("Payment failed: Insufficient funds.");
            logPayment(order, paymentDetails, totalPrice, tipPercentage,false);
            return false;
        }

        // If funds are successfully subtracted, proceed with the rest of the payment processing
        boolean paymentSuccess = true; // This should actually be the result of the payment transaction

        // Log the successful payment transaction
        logPayment(order, paymentDetails, totalPrice, tipPercentage, paymentSuccess);

        // Update order status in the database based on the outcome of the payment
        PostgreSQL.updateOrderStatus(order.getOrderID(), paymentSuccess ? "Paid" : "Payment Failed");

        // Output the payment status message
        System.out.println(paymentSuccess ? "Payment successful!" : "Payment failed!");

        return paymentSuccess;
    }

    private static void logPayment(Order order, PaymentDetails paymentDetails,
                                   double totalAmount, double tipPercentage, boolean paymentSuccess) {
        // Implement the logic to log the payment transaction in the database
        PostgreSQL.logPaymentTransaction(order.getOrderID(),
                paymentDetails.getPaymentMethod(),
                paymentDetails.getIdentifier(),
                totalAmount,
                tipPercentage,
                paymentSuccess);
    }
}
