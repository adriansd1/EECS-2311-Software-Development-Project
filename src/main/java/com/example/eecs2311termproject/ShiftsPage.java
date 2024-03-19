package com.example.eecs2311termproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShiftsPage {

    //Set-up and display GUI
    public static void display() {
        //Setting stage and container
        Stage employeeStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        //Home button to close menu
        Button homeButton = new Button("Return to Home");

        Label titleLabel = new Label("Shifts");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        // Pin entry
        Label pinLabel = new Label("Enter Pin:");
        PasswordField pinField = new PasswordField();
        Button confirmButton = new Button("Confirm");

        // On action to close menu when pressing home button
        homeButton.setOnAction(e -> {
            employeeStage.close();
        });

        // On action to validate the pin and login
        confirmButton.setOnAction(e -> {
            String pin = pinField.getText();
            // Perform validation of the pin here, and if valid, proceed to employee's page
            if (isValidPin(pin)) {
                // Proceed to show shifts
                showShifts(layout, employeeStage);
            } else {
                // Handle incorrect pin
                System.out.println("Invalid Pin");
            }
        });

        // Add title, pin entry, confirm button, home button to layout
        layout.getChildren().addAll(titleLabel, pinLabel, pinField, confirmButton, homeButton);
        Scene scene = new Scene(layout, 400, 300);
        // Set and show scene
        employeeStage.setScene(scene);
        employeeStage.show();
    }

    // Dummy method for pin validation
    private static boolean isValidPin(String pin) {
        // Replace this with actual pin validation logic
        return "1234".equals(pin);
    }

    // Method to show shifts with employee names
    private static void showShifts(VBox layout, Stage stage) {
        layout.getChildren().clear(); // Clear existing content
        GridPane shiftsGrid = new GridPane();
        shiftsGrid.setAlignment(Pos.CENTER);
        shiftsGrid.setHgap(10);
        shiftsGrid.setVgap(10);
        shiftsGrid.setPadding(new Insets(20));

        // Add employee names to squares
        String[] employees = {"Alice", "Bob", "Charlie"};
        for (int i = 0; i < employees.length; i++) {
            Label employeeLabel = new Label(employees[i]);
            employeeLabel.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
            shiftsGrid.add(employeeLabel, i, 0);
        }

        // Add grid to layout
        layout.getChildren().add(shiftsGrid);
        // Update the scene
        stage.sizeToScene();
    }
}
