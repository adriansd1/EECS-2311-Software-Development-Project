package com.example.eecs2311termproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private static void showShifts(VBox layout, Stage stage) {
        layout.getChildren().clear(); // Clear existing content
        GridPane shiftsGrid = new GridPane();
        shiftsGrid.setAlignment(Pos.CENTER);
        shiftsGrid.setHgap(10);
        shiftsGrid.setVgap(10);
        shiftsGrid.setPadding(new Insets(20));

        // Add employee names, roles, time worked, clock in, and clock out buttons to squares
        String[][] employees = {{"Alice", "Waiter"}, {"Bob", "Chef"}, {"Charlie", "Manager"}};
        for (int i = 0; i < employees.length; i++) {
            Label nameLabel = new Label(employees[i][0]);
            Label roleLabel = new Label(employees[i][1]);
            Label timeLabel = new Label("Time Worked: 0 hours 0 minutes 0 seconds"); // Initial time worked
            Button clockInButton = new Button("Clock In");
            Button clockOutButton = new Button("Clock Out");

            VBox personBox = new VBox(5);
            personBox.setAlignment(Pos.CENTER);
            personBox.getChildren().addAll(nameLabel, roleLabel, timeLabel, clockInButton, clockOutButton);
            personBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");

            // Clock in action
            int finalI = i;
            Timeline clockInTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                // Update time worked label
                String[] timeParts = timeLabel.getText().split(" ");
                int hours = Integer.parseInt(timeParts[2]);
                int minutes = Integer.parseInt(timeParts[4]);
                int seconds = Integer.parseInt(timeParts[6]);
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                    if (minutes == 60) {
                        minutes = 0;
                        hours++;
                    }
                }
                timeLabel.setText("Time Worked: " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
            }));
            clockInButton.setOnAction(e -> {
                clockInTimer.setCycleCount(Timeline.INDEFINITE);
                clockInTimer.play();
            });

            // Clock out action
            int finalI1 = i;
            clockOutButton.setOnAction(e -> {
                clockInTimer.stop();
                System.out.println("Clock Out: " + employees[finalI1][0] + " Total Time Worked: " + timeLabel.getText());
            });

            shiftsGrid.add(personBox, i, 0);
        }

        // Add grid to layout
        layout.getChildren().add(shiftsGrid);
        // Update the scene
        stage.sizeToScene();
    }



}
