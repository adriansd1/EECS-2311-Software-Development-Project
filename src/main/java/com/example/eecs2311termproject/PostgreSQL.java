package com.example.eecs2311termproject;

import java.sql.*;

public class PostgreSQL {
    public static void WriteToDatabase(String foodName, double price, int quantity){
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "shitijagg7";

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

    public static void deleteFood(String foodName) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            //Later on delete by their table number as well too(maybe every table in the resturant has table in db)
            String deleteQuery = "DELETE FROM \"Orders\" WHERE \"Food name\" = ?";
            try (PreparedStatement pst = con.prepareStatement(deleteQuery)) {
                pst.setString(1, foodName);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Food deleted successfully!");
                } else {
                    System.out.println("Failed to delete food!");
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

    public static String readFoodNameFromDatabase(int rowNum) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        String foodName = null;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "SELECT \"Food name\" FROM \"Orders\" OFFSET ? LIMIT 1";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, rowNum - 1); // Offset starts from 0, so subtract 1 from rowNum
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    foodName = rs.getString("Food name");
                } else {
                    System.out.println("Row " + rowNum + " not found!");
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return foodName;
    }

    public static double readPriceFromDatabase(int rowNum) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        double price = 0.0;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "SELECT \"Price\" FROM \"Orders\" OFFSET ? LIMIT 1";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, rowNum - 1); // Offset starts from 0, so subtract 1 from rowNum
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    price = rs.getDouble("Price");
                } else {
                    System.out.println("Row " + rowNum + " not found!");
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return price;
    }

    public static int readQuantityFromDatabase(int rowNum) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        int quantity = 0;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "SELECT \"Quantity\" FROM \"Orders\" OFFSET ? LIMIT 1";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, rowNum - 1); // Offset starts from 0, so subtract 1 from rowNum
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("Quantity");
                } else {
                    System.out.println("Row " + rowNum + " not found!");
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return quantity;
    }

    public static int getRowCount() {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        int rowCount = 0;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "SELECT COUNT(*) AS row_count FROM \"Orders\"";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    rowCount = rs.getInt("row_count");
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return rowCount;
    }

    // Method with intention to improve PaymentHandler by changing the order's status to "Paid", "Failed".
    public static void updateOrderStatus(String orderId, String newStatus) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Ahmad";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "UPDATE Orders SET Status = ? WHERE OrderID = ?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, newStatus);
                pst.setString(2, orderId);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Order status updated successfully!");
                } else {
                    System.out.println("Failed to update order status.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed: " + e.getMessage());
        }
    }

    public static void logPaymentTransaction(String orderId, String paymentMethod, String identifier, double amountPaid, boolean success) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Ahmad";
        String query = "INSERT INTO PaymentLog (OrderID, PaymentMethod, Identifier, AmountPaid, Success) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, orderId);
            pst.setString(2, paymentMethod);
            pst.setString(3, identifier); // Consider storing only masked or partial identifiers for security
            pst.setDouble(4, amountPaid);
            pst.setBoolean(5, success);
            pst.executeUpdate();

            System.out.println("Payment transaction logged successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to log payment transaction: " + e.getMessage());
        }
    }

    public static boolean validatePaymentMethod(String paymentMethod, String identifier) {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String password = "Ahmad";

        String query = "SELECT Valid FROM MockPaymentDetails WHERE PaymentMethod = ? AND Identifier = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, paymentMethod);
            pst.setString(2, identifier);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Check if the payment method is marked as valid in the database
                    return rs.getBoolean("Valid");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed: " + e.getMessage());
        }
        // Return false if the payment method is not found or an exception occurs
        return false;
    }


}


