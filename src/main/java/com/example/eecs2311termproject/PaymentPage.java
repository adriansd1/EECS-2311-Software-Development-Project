package com.example.eecs2311termproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class PaymentPage extends Application {

    private static final String url = "jdbc:postgresql:postgres";
    private static final String user = "postgres";
    private static final String password = "Adrian";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Payment Page");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Title label
        Label titleLabel = new Label("Checkout");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black;");

        // ComboBox for table numbers
        ComboBox<Integer> tableComboBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
        tableComboBox.setPromptText("Select table number");

        // Button to get the bill
        Button getBillButton = new Button("Get my Bill");
        getBillButton.setOnAction(e -> {
            int tableNumber = tableComboBox.getValue();
            if (tableNumber != 0) {
                double total = getTotalForTable(tableNumber);
                showBill(total, tableNumber);
            } else {
                showAlert("Please select a table number.");
            }
        });

        vbox.getChildren().addAll(titleLabel, tableComboBox, getBillButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private double getTotalForTable(int tableNumber) {
        double total = 0.0;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT SUM(\"Price\" * \"Quantity\") FROM \"Orders\" WHERE \"TableNumber\" = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, tableNumber);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        total = rs.getDouble(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    private void showBill(double total, int tableNumber) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bill Information");
        alert.setHeaderText(null);

        // Add option to enter tip amount
        TextField tipField = new TextField();
        tipField.setPromptText("Enter tip amount");

        VBox content = new VBox();
        content.getChildren().add(new Label("Total before tip: $" + total));
        content.getChildren().add(new Label("Enter tip amount:"));
        content.getChildren().add(tipField);

        alert.getDialogPane().setContent(content);

        ButtonType calculateButton = new ButtonType("Calculate Total");
        alert.getButtonTypes().add(calculateButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == calculateButton) {
                double tip = 0.0;
                try {
                    tip = Double.parseDouble(tipField.getText());
                } catch (NumberFormatException e) {
                    showAlert("Please enter a valid tip amount.");
                    return;
                }

                double totalAfterTip = total + tip;

                // Display receipt
                StringBuilder receiptText = new StringBuilder();
                receiptText.append("Total before tip: $").append(total).append("\n");
                receiptText.append("Tip: $").append(tip).append("\n");
                receiptText.append("Total after tip: $").append(totalAfterTip).append("\n\n");
                receiptText.append("Items:\n");

                try (Connection con = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM \"Orders\" WHERE \"TableNumber\" = ?";
                    try (PreparedStatement pst = con.prepareStatement(query)) {
                        pst.setInt(1, tableNumber);
                        try (ResultSet rs = pst.executeQuery()) {
                            while (rs.next()) {
                                String foodName = rs.getString("Food name");
                                double price = rs.getDouble("Price");
                                int quantity = rs.getInt("Quantity");
                                receiptText.append("- ").append(foodName).append(" (Quantity: ").append(quantity).append("): $").append(price * quantity).append("\n");
                            }
                        }
                    }

                    // Delete all items from the table
                    String deleteQuery = "DELETE FROM \"Orders\" WHERE \"TableNumber\" = ?";
                    try (PreparedStatement deletePst = con.prepareStatement(deleteQuery)) {
                        deletePst.setInt(1, tableNumber);
                        deletePst.executeUpdate();
                    }

                    // Delete the row from the "Tables" table
                    deleteQuery = "DELETE FROM \"Tables\" WHERE \"TableNumber\" = ?";
                    try (PreparedStatement deletePst = con.prepareStatement(deleteQuery)) {
                        deletePst.setInt(1, tableNumber);
                        deletePst.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Show receipt
                Alert receiptAlert = new Alert(Alert.AlertType.INFORMATION);
                receiptAlert.setTitle("Receipt");
                receiptAlert.setHeaderText(null);
                receiptAlert.setContentText(receiptText.toString());
                receiptAlert.showAndWait();
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
