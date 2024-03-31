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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class ShiftsPage {

    private static ObservableList<String[]> employees = FXCollections.observableArrayList();

    public static void loadEmployees() {
        employees.clear();
        employees.addAll(PostgreSQL.getAllEmployees());
    }

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

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Scene scene = new Scene(scrollPane, 400, 300);
        employeeStage.setScene(scene);
        employeeStage.show();

        loadEmployees(); // Load employees from the database
        showShifts(layout, employeeStage);
    }

    private static void showShifts(VBox layout, Stage stage) {
        layout.getChildren().removeIf(node -> node instanceof GridPane); // Remove existing content
        GridPane shiftsGrid = new GridPane();
        shiftsGrid.setAlignment(Pos.CENTER);
        shiftsGrid.setHgap(10);
        shiftsGrid.setVgap(10);
        shiftsGrid.setPadding(new Insets(20));

        int rowCount = (int) Math.ceil((double) employees.size() / 3); // Calculate number of rows based on employee count

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
            Button endShiftButton = new Button("End Shift");

            VBox personBox = new VBox(5);
            personBox.setAlignment(Pos.CENTER);
            personBox.getChildren().addAll(nameLabel, roleLabel, timeLabel, editButton, deleteButton, toggleViewButton, clockInButton, clockOutButton, endShiftButton);
            personBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");

            editButton.setVisible(false);
            deleteButton.setVisible(false);

            int rowIndex = i % rowCount; // Calculate row index based on current employee index
            int colIndex = i / rowCount; // Calculate column index based on current employee index

            int finalI = i;
            editButton.setOnAction(e -> {
                editEmployee(finalI, layout, stage);
            });

            int finalI1 = i;
            deleteButton.setOnAction(e -> {
                deleteEmployee(finalI1, layout, stage);
            });

            toggleViewButton.setOnAction(e -> {
                if (timeLabel.isVisible()) {
                    timeLabel.setVisible(false);
                    editButton.setVisible(true);
                    deleteButton.setVisible(true);
                    clockInButton.setVisible(false);
                    clockOutButton.setVisible(false);
                    endShiftButton.setVisible(false);
                } else {
                    timeLabel.setVisible(true);
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                    clockInButton.setVisible(true);
                    clockOutButton.setVisible(true);
                    endShiftButton.setVisible(true);
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
                System.out.println("Clock Out: " + employee[0] + " " + timeLabel.getText());
            });

            endShiftButton.setOnAction(e -> {
                clockInTimer.stop();
                System.out.println("Shift Ended: " + employee[0] + " " + timeLabel.getText());
                timeLabel.setText("Time Worked: 0 hours 0 minutes 0 seconds");
            });

            shiftsGrid.add(personBox, colIndex, rowIndex);
        }

        layout.getChildren().add(shiftsGrid);
        stage.sizeToScene();
    }




    private static void addEmployee(VBox layout, Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField roleField = new TextField();
        roleField.setPromptText("Role");
        TextField salaryField = new TextField();
        salaryField.setPromptText("Salary");

        HBox inputBox = new HBox(10, nameField, roleField, salaryField);

        Alert addEmployeeDialog = new Alert(Alert.AlertType.CONFIRMATION);
        addEmployeeDialog.setTitle("Add Employee");
        addEmployeeDialog.setHeaderText("Enter Employee Details:");
        addEmployeeDialog.getDialogPane().setContent(inputBox);

        Optional<ButtonType> result = addEmployeeDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String name = nameField.getText();
            String role = roleField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            employees.add(new String[]{name, role, String.valueOf(salary)});
            PostgreSQL.addEmployee(name, role, salary);
            showShifts(layout, stage);
        }
    }

    private static void editEmployee(int index, VBox layout, Stage stage) {
        String[] employee = employees.get(index);

        TextField nameField = new TextField(employee[0]);
        TextField roleField = new TextField(employee[1]);
        TextField salaryField = new TextField(employee[2]);

        HBox inputBox = new HBox(10, nameField, roleField, salaryField);

        Alert editEmployeeDialog = new Alert(Alert.AlertType.CONFIRMATION);
        editEmployeeDialog.setTitle("Edit Employee");
        editEmployeeDialog.setHeaderText("Enter Employee Details:");
        editEmployeeDialog.getDialogPane().setContent(inputBox);

        Optional<ButtonType> result = editEmployeeDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String name = nameField.getText();
            String role = roleField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            employees.set(index, new String[]{name, role, String.valueOf(salary)});
            PostgreSQL.updateEmployee(employee[0], name, role, salary);
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
            // Get the employee's name and role
            String[] employee = employees.get(index);
            String name = employee[0];
            String role = employee[1];

            // Delete the employee from the database
            PostgreSQL.deleteEmployee(name, role);

            // Remove the employee from the list and update the UI
            employees.remove(index);
            showShifts(layout, stage);
        }
    }

}
