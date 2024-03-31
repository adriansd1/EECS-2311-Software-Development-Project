package com.example.eecs2311termproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQL {
    public static void WriteToDatabase(String foodName, double price, int quantity, Integer selectedTableNumber){
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        if(ClientSide.AYCE){
            price = 0;
        }

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "INSERT INTO \"Orders\"(\"Food name\", \"Price\", \"Quantity\", \"IsCompleted\", \"TableNumber\") VALUES(?, ?, ?, false, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, foodName);
                pst.setDouble(2, price);
                pst.setInt(3, quantity);
                pst.setInt(4, selectedTableNumber);

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

    public static void addEmployee(String name, String role, double wage) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "INSERT INTO \"Employees\" (\"Name\", \"Role\", \"Wage\") VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, name);
                pst.setString(2, role);
                pst.setDouble(3, wage);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Employee added successfully!");
                } else {
                    System.out.println("Failed to add employee!");
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

    public static void updateEmployee(String oldName, String newName, String role, double wage) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "UPDATE \"Employees\" SET \"Name\" = ?, \"Role\" = ?, \"Wage\" = ? WHERE \"Name\" = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, newName);
                pst.setString(2, role);
                pst.setDouble(3, wage);
                pst.setString(4, oldName);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Employee updated successfully!");
                } else {
                    System.out.println("Failed to update employee!");
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


    public static void deleteEmployee(String name, String role) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "DELETE FROM \"Employees\" WHERE \"Name\" = ? AND \"Role\" = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, name);
                pst.setString(2, role);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Employee deleted successfully!");
                } else {
                    System.out.println("Failed to delete employee!");
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

    public static List<String[]> getAllEmployees() {
        List<String[]> employees = new ArrayList<>();
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "SELECT * FROM \"Employees\"";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String name = rs.getString("Name");
                    String role = rs.getString("Role");
                    double wage = rs.getDouble("Wage");
                    employees.add(new String[]{name, role, String.valueOf(wage)});
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        return employees;
    }

    public static void markCompleted(String foodName) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "UPDATE \"Orders\" SET \"IsCompleted\" = true WHERE \"Food name\" = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, foodName);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Order marked as completed successfully!");
                } else {
                    System.out.println("Failed to mark order as completed!");
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

    public static boolean isOrderCompleted(int orderId) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";
        boolean completed = false;

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT \"IsCompleted\" FROM (SELECT \"IsCompleted\", ROW_NUMBER() OVER () AS row_num FROM \"Orders\") AS temp WHERE row_num = ?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, orderId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        completed = rs.getBoolean("IsCompleted");
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        return completed;
    }

    public static int readTableNumberFromDataBase(int rowNum) {
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "Adrian";

        int tableNumber = 0;
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            String query = "SELECT \"TableNumber\" FROM \"Orders\" OFFSET ? LIMIT 1";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, rowNum - 1); // Offset starts from 0, so subtract 1 from rowNum
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    tableNumber = rs.getInt("TableNumber");
                } else {
                    System.out.println("TableNumber " + rowNum + " not found!");
                }
            } catch (SQLException e) {
                System.out.println("Error executing query!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return tableNumber;
    }
}
