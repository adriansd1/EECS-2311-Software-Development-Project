package com.example.eecs2311termproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentHandlerTest {

    private Order order;
    private Food californiaRoll;
    private Food salmonNigiri;

    @BeforeEach
    void setUp() {
        order = new Order(1); // Order allocated to table 1

        // Creating food items
        californiaRoll = new Food("California Roll", 8.0);
        salmonNigiri = new Food("Salmon Nigiri", 5.0);
    }

    @Test
    void calculateTotalPrice() {
        order.addFood(californiaRoll);
        order.addFood(salmonNigiri);

        double totalPrice = PaymentHandler.calculateTotalPrice(order);

        assertEquals(13.0, totalPrice, 0.01, "Calculating total price should be correct.");
    }

    @Test
    void processPaymentSuccessfully() {
        order.setRunningTotal(20.0);
        double paymentAmount = 20.0;

        boolean result = PaymentHandler.processPayment(order, paymentAmount, 0.0); // Assuming no tip

        assertTrue(result, "Payment should be processed successfully.");
        assertEquals("Paid", order.getStatus(), "Order status should be updated to 'Paid'.");
    }

    @Test
    void processPaymentUnsuccessfully() {
        order.setRunningTotal(25.0);
        double paymentAmount = 20.0;

        boolean result = PaymentHandler.processPayment(order, paymentAmount, 0.0); // Assuming no tip

        assertFalse(result, "Payment should not be processed due to insufficient funds.");
        assertNotEquals("Paid", order.getStatus(), "Order status should not be 'Paid' after unsuccessful payment.");
    }

    @Test
    void processPaymentWithTip() {
        order.setRunningTotal(25.0);
        double paymentAmount = 30.0; // Paying 30 dollars, with a tip of 5 dollars

        boolean result = PaymentHandler.processPayment(order, paymentAmount, 20.0); // Tip of 20%

        assertTrue(result, "Payment should be processed successfully with tip.");
        assertEquals("Paid", order.getStatus(), "Order status should be updated to 'Paid'.");
    }

    @Test
    void splitBillEqually() {
        order.addFood(californiaRoll);
        order.addFood(salmonNigiri);

        double totalPrice = PaymentHandler.calculateTotalPrice(order);

        // Splitting the bill equally into two payments
        int numPayments = 2;
        double paymentAmount = totalPrice / numPayments;

        // Process payments
        boolean result = PaymentHandler.processPayment(order, paymentAmount, 0.0);
        assertTrue(result, "Payments should be successful.");

        assertEquals("Paid", order.getStatus(), "Order status should be updated to 'Paid'.");
    }

    @Test
    void splitBillUnequally() {
        order.addFood(californiaRoll);
        order.addFood(salmonNigiri);

        double totalPrice = PaymentHandler.calculateTotalPrice(order);

        // Splitting the bill unequally into two payments
        int numPayments = 2;
        double paymentAmount1 = totalPrice * 0.6;
        double paymentAmount2 = totalPrice * 0.4;

        // Process payments
        boolean result = PaymentHandler.processPayment(order, paymentAmount1 + paymentAmount2, 0.0);
        assertTrue(result, "Payments should be successful.");

        assertEquals("Paid", order.getStatus(), "Order status should be updated to 'Paid'.");
    }


    @Test
    void insufficientFunds() {
        order.addFood(californiaRoll);
        order.addFood(salmonNigiri);

        double totalPrice = PaymentHandler.calculateTotalPrice(order);

        // Insufficient funds for the first payment
        int numPayments = 2;
        double paymentAmount = totalPrice / 2.0 - 5.0;

        // Process payments
        boolean result = PaymentHandler.processPayment(order, paymentAmount + totalPrice / 2.0, 0.0);
        assertFalse(result, "First payment should fail due to insufficient funds.");

        assertEquals("Pending", order.getStatus(), "Order status should remain 'Pending'.");
    }
}
