package com.example.eecs2311termproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class ShiftsPage {

    private static ObservableList<String[]> employees = FXCollections.observableArrayList(
            new String[]{"Alice", "Waiter"},
            new String[]{"Bob", "Chef"},
            new String[]{"Charlie", "Manager"}
    );

    public static void display() {
        Stage employeeStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        Button homeButton = new Button("Return to Home");

        Label titleLabel = new Label("Shifts");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        Button addEmployeeButton = new Button("Add Employee");

        homeButton.setOnAction(e -> {
            employeeStage.close();
        });

        addEmployeeButton.setOnAction(e -> {
            addEmployee(layout, employeeStage);
        });

        layout.getChildren().addAll(titleLabel, homeButton, addEmployeeButton);
        Scene scene = new Scene(layout, 400, 300);
        employeeStage.setScene(scene);
        employeeStage.show();

        showShifts(layout, employeeStage);
    }

    private static void showShifts(VBox layout, Stage stage) {
        layout.getChildren().removeIf(node -> node instanceof GridPane); // Remove existing content
        GridPane shiftsGrid = new GridPane();
        shiftsGrid.setAlignment(Pos.CENTER);
        shiftsGrid.setHgap(10);
        shiftsGrid.setVgap(10);
        shiftsGrid.setPadding(new Insets(20));

        for (int i = 0; i < employees.size(); i++) {
            String[] employee = employees.get(i);
            Label nameLabel = new Label(employee[0]);
            Label roleLabel = new Label(employee[1]);
            Label timeLabel = new Label("Time Worked: 0 hours 0 minutes 0 seconds");
            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");
            Button toggleViewButton = new Button("Toggle View");
            Button clockInButton = new Button("Clock In");
            Button clockOutButton = new Button("Clock Out");

            VBox personBox = new VBox(5);
            personBox.setAlignment(Pos.CENTER);
            personBox.getChildren().addAll(nameLabel, roleLabel, timeLabel, editButton, deleteButton, toggleViewButton, clockInButton, clockOutButton);
            personBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");

            editButton.setVisible(false);
            deleteButton.setVisible(false);

            int index = i;
            editButton.setOnAction(e -> {
                editEmployee(index, layout, stage);
            });

            deleteButton.setOnAction(e -> {
                deleteEmployee(index, layout, stage);
            });

            toggleViewButton.setOnAction(e -> {
                if (timeLabel.isVisible()) {
                    timeLabel.setVisible(false);
                    editButton.setVisible(true);
                    deleteButton.setVisible(true);
                    clockInButton.setVisible(false);
                    clockOutButton.setVisible(false);
                } else {
                    timeLabel.setVisible(true);
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                    clockInButton.setVisible(true);
                    clockOutButton.setVisible(true);
                }
            });

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

            clockOutButton.setOnAction(e -> {
                clockInTimer.stop();
                System.out.println("Clock Out: " + employee[0] + " Total Time Worked: " + timeLabel.getText());
            });

            shiftsGrid.add(personBox, i % 3, i / 3);
        }

        layout.getChildren().add(shiftsGrid);
        stage.sizeToScene();
    }


    private static void addEmployee(VBox layout, Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField roleField = new TextField();
        roleField.setPromptText("Role");

        HBox inputBox = new HBox(10, nameField, roleField);

        Alert addEmployeeDialog = new Alert(Alert.AlertType.CONFIRMATION);
        addEmployeeDialog.setTitle("Add Employee");
        addEmployeeDialog.setHeaderText("Enter Employee Details:");
        addEmployeeDialog.getDialogPane().setContent(inputBox);

        Optional<ButtonType> result = addEmployeeDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String name = nameField.getText();
            String role = roleField.getText();
            employees.add(new String[]{name, role});
            showShifts(layout, stage);
        }
    }

    private static void editEmployee(int index, VBox layout, Stage stage) {
        String[] employee = employees.get(index);

        TextField nameField = new TextField(employee[0]);
        TextField roleField = new TextField(employee[1]);

        HBox inputBox = new HBox(10, nameField, roleField);

        Alert editEmployeeDialog = new Alert(Alert.AlertType.CONFIRMATION);
        editEmployeeDialog.setTitle("Edit Employee");
        editEmployeeDialog.setHeaderText("Enter Employee Details:");
        editEmployeeDialog.getDialogPane().setContent(inputBox);

        Optional<ButtonType> result = editEmployeeDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String name = nameField.getText();
            String role = roleField.getText();
            employees.set(index, new String[]{name, role});
            showShifts(layout, stage);
        }
    }

    private static void deleteEmployee(int index, VBox layout, Stage stage) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete Employee");
        confirmation.setHeaderText("Are you sure you want to delete this employee?");
        confirmation.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            employees.remove(index);
            showShifts(layout, stage);
        }
    }

}
