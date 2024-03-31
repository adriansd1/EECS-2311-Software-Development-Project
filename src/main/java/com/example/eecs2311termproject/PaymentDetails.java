package com.example.eecs2311termproject;

public class PaymentDetails {
    private String paymentMethod;
    private String identifier; // Mock card number or PayPal email


    public PaymentDetails(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentDetails(String paymentMethod, String identifier) {
        this.paymentMethod = paymentMethod;
        this.identifier = identifier;
    }

    // Getters
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getIdentifier() {
        return identifier;
    }

    // Setters as needed
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}

