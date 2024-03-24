package com.example.eecs2311termproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

class PaymentHandlerTest {

    private Order order;
    private Food californiaRoll;
    private Food salmonNigiri;
    private PaymentDetails paymentDetails;

    @BeforeEach
    void setUp() {
        order = new Order(1); // Order allocated to table 1
        californiaRoll = new Food("California Roll", 8.0);
        salmonNigiri = new Food("Salmon Nigiri", 5.0);

        // Add food items to the order
        order.addFood(californiaRoll);
        order.addFood(salmonNigiri);

        // Initialize PaymentDetails
        paymentDetails = new PaymentDetails("CreditCard", "1234567890123456");
    }

    @Test
    void testCalculateTotalPrice() {
        double expectedTotalPrice = californiaRoll.getPrice() + salmonNigiri.getPrice();
        double actualTotalPrice = PaymentHandler.calculateTotalPrice(order);
        assertEquals(expectedTotalPrice, actualTotalPrice, "The total price should be the sum of the prices of all food items in the order.");
    }

    @Test
    void testSuccessfulPayment() {
        // Adjust the payment amount in PaymentDetails as needed or ensure it's valid
        boolean result = PaymentHandler.processPayment(order, paymentDetails);
        assertTrue(result, "Payment should be processed successfully when the correct amount is provided.");
    }

    @Test
    void testUnsuccessfulPayment() {
        // Use a different setup for PaymentDetails to simulate an unsuccessful scenario
        PaymentDetails insufficientPaymentDetails = new PaymentDetails("InvalidMethod", "000");
        boolean result = PaymentHandler.processPayment(order, insufficientPaymentDetails);
        assertFalse(result, "Payment should fail when an invalid payment method is provided.");
    }
}
