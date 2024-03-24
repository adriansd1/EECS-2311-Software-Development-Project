package com.example.eecs2311termproject;
import com.example.eecs2311termproject.PaymentHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;



public class PaymentPage {

    public static void display(Order currentOrder) {
        Stage window = new Stage();
        window.setTitle("Payment");

        // Labels and fields for payment information
        Label paymentMethodLabel = new Label("Payment Method (CreditCard/PayPal):");
        TextField paymentMethodField = new TextField();

        Label identifierLabel = new Label("Card Number or PayPal Email:");
        TextField identifierField = new TextField();

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField(String.valueOf(currentOrder.getRunningTotal()));
        amountField.setEditable(false);

        Button submitPaymentButton = new Button("Submit Payment");
        submitPaymentButton.setOnAction(e -> {
            PaymentDetails paymentDetails = new PaymentDetails(paymentMethodField.getText(), identifierField.getText());
            boolean paymentSuccess = PaymentHandler.processPayment(currentOrder, paymentDetails);

            // Feedback to the user
            if (paymentSuccess) {
                processPaymentAlert("Payment Success", null, "Your payment has been successfully processed.");
            } else {
                processPaymentAlert("Payment Failed", null, "There was an issue processing your payment. Please try again.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(paymentMethodLabel, paymentMethodField, identifierLabel, identifierField, amountLabel, amountField, submitPaymentButton);
        layout.setAlignment(Pos.CENTER);
        // Adjusting VBox padding
        layout.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        // Centering the window based on the screen size (optional)
        window.centerOnScreen();
        window.showAndWait();
    }

    private static void processPaymentAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title); // Set the title of the alert
        alert.setHeaderText(header); // Optionally set the header text
        alert.setContentText(content); // Set the content text
        alert.showAndWait(); // Display the alert and wait for it to be dismissed
    }
}
