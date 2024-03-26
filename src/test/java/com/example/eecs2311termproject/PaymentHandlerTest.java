/*package com.example.eecs2311termproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

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

        boolean result = PaymentHandler.processPayment(order, paymentAmount);

        assertTrue(result, "Payment should be processed successfully.");
        assertEquals("Paid", order.getStatus(), "Order status should be updated to 'Paid'.");
    }

    @Test
    void processPaymentUnsuccessfully() {
        order.setRunningTotal(25.0);
        double paymentAmount = 20.0;

        boolean result = PaymentHandler.processPayment(order, paymentAmount);

        assertFalse(result, "Payment should not be processed due to insufficient funds.");
        assertNotEquals("Paid", order.getStatus(), "Order status should not be 'Paid' after unsuccessful payment.");
    }
}*/
