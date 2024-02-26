package com.example.eecs2311termproject;

import java.sql.*;

public class PostgreSQL {
    public static void WriteToDatabase(String foodName, double price, int quantity){
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        String foodName1 = foodName;
        double foodPrice = price;
        int foodQuantity = quantity;

        //String query = "INSERT INTO Orders(foodName1, foodPrice, foodQuantity) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "INSERT INTO \"Orders\"(\"Food name\", \"Price\", \"Quantity\") VALUES(?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, foodName);
                pst.setDouble(2, price);
                pst.setInt(3, quantity);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data!");
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public static void updateQuantity(String foodName, int quantityToAdd) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String selectQuery = "SELECT \"Quantity\" FROM \"Orders\" WHERE \"Food name\" = ?";
            try (PreparedStatement selectPst = con.prepareStatement(selectQuery)) {
                selectPst.setString(1, foodName);
                ResultSet rs = selectPst.executeQuery();

                int currentQuantity = 0;
                if (rs.next()) {
                    currentQuantity = rs.getInt("Quantity");
                }

                int newQuantity = currentQuantity + quantityToAdd;

                String updateQuery = "UPDATE \"Orders\" SET \"Quantity\" = ? WHERE \"Food name\" = ?";
                try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                    updatePst.setInt(1, newQuantity);
                    updatePst.setString(2, foodName);

                    int rowsAffected = updatePst.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Quantity updated successfully!");
                    } else {
                        System.out.println("Failed to update quantity!");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing query!");
            e.printStackTrace();
        }
    }

}
