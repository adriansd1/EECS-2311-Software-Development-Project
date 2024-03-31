package com.example.eecs2311termproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class PaymentHistory extends Application {

    private static final String url = "jdbc:postgresql:postgres";
    private static final String user = "postgres";
    private static final String password = "Adrian";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Payment History");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(20));

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM \"PaymentHistory\"";
            try (PreparedStatement pst = con.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int tableNumber = rs.getInt("TableNumber");
                    double totalBeforeTip = rs.getDouble("TotalBeforeTip");
                    double tipAmount = rs.getDouble("TipAmount");
                    double totalAfterTip = rs.getDouble("TotalAfterTip");

                    Label paymentLabel = new Label("Table Number: " + tableNumber + "\n" +
                            "Total before tip: $" + totalBeforeTip + "\n" +
                            "Tip amount: $" + tipAmount + "\n" +
                            "Total after tip: $" + totalAfterTip + "\n");
                    vbox.getChildren().add(paymentLabel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scrollPane.setContent(vbox);

        Scene scene = new Scene(scrollPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
