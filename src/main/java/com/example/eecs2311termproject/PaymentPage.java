package com.example.eecs2311termproject;
import com.example.eecs2311termproject.PaymentHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;


public class PaymentPage {

    public static void display(Order currentOrder) {
        Stage window = new Stage();
        window.setTitle("Payment");

        Label paymentMethodLabel = new Label("Payment Method:");
        ToggleButton paypalButton = new ToggleButton("PayPal");
        ToggleButton creditCardButton = new ToggleButton("Credit Card");

        // Create a toggle group for the buttons
        ToggleGroup paymentMethodGroup = new ToggleGroup();
        paypalButton.setToggleGroup(paymentMethodGroup);
        creditCardButton.setToggleGroup(paymentMethodGroup);

        PaymentDetails paymentDetails = new PaymentDetails(""); // Initialize with an empty string or appropriate default value


        // Handle button selection events
        paymentMethodGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedMethod = ((ToggleButton) newValue).getText();
                paymentDetails.setPaymentMethod(selectedMethod);
            }
        });

        Label identifierLabel = new Label("Card Number or PayPal Email:");
        TextField identifierField = new TextField();

        Label addTipLabel = new Label("Add Tip?");
        TextField tipField = new TextField();


        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField(String.valueOf(ClientSide.clientOrder.getRunningTotal()));
        amountField.setEditable(false);


        Button submitPaymentButton = new Button("Submit Payment");
        submitPaymentButton.setOnAction(e -> {
            paymentDetails.setIdentifier(identifierField.getText());
            double tip = 0;
            try {
                tip = Double.parseDouble(tipField.getText()); // Safely parse the tip amount
            } catch (NumberFormatException ex) {
                processPaymentAlert("Invalid Tip", null, "Please enter a valid tip amount.");
                return; // Stop processing since the tip amount is invalid
            }

            boolean paymentSuccess = PaymentHandler.processPayment(currentOrder, paymentDetails, tip);

            // Feedback to the user
            if (paymentSuccess) {
                processPaymentAlert("Payment Success", null, "Your payment has been successfully processed.");
            } else {
                processPaymentAlert("Payment Failed", null, "There was an issue processing your payment. Please try again.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                paymentMethodLabel,
                paypalButton,
                creditCardButton,
                identifierLabel,
                identifierField,
                addTipLabel,
                tipField,
                amountLabel,
                amountField,
                submitPaymentButton
        );
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
