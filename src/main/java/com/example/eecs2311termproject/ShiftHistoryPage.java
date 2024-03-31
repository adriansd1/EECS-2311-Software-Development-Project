package com.example.eecs2311termproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShiftHistoryPage {

    private static final String url = "jdbc:postgresql:postgres";
    private static final String user = "postgres";
    private static final String password = "Adrian";

    public static void display() {
        Stage historyStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);

        Scene scene = new Scene(scrollPane, 400, 300);
        historyStage.setScene(scene);
        historyStage.show();

        showShiftHistory(layout);
    }

    private static void showShiftHistory(VBox layout) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"ShiftHistory\"");

            ObservableList<Label> labels = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String name = resultSet.getString("employee_name");
                String shiftLength = resultSet.getString("shift_length");
                Label label = new Label(name + ": " + shiftLength);
                labels.add(label);
            }

            layout.getChildren().addAll(labels);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
