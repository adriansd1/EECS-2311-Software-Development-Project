package com.example.eecs2311termproject;

import org.junit.jupiter.api.*;


import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PostgreSQLTest {


    private static final String url = "jdbc:postgresql:postgres";
    private static final String user = "postgres";
    private static final String password = "";

    private static Connection connection;

    @BeforeAll
    public static void setup() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL database!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanup() {
        try {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DELETE FROM \"Orders\"");
                }
                System.out.println("Deleted all rows in the table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @BeforeEach
    public void beforeEach() {
        // Reset the database state before each test
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS \"Orders\"");
            statement.executeUpdate("DROP TABLE IF EXISTS \"Tables\"");
            statement.executeUpdate("DROP TABLE IF EXISTS \"Employees\"");
            statement.executeUpdate("CREATE TABLE \"Orders\" (\"Food name\" VARCHAR(255), \"Price\" DOUBLE PRECISION, \"Quantity\" INTEGER, \"IsCompleted\" BOOLEAN, \"TableNumber\" INTEGER)");
            statement.executeUpdate("CREATE TABLE \"Tables\" (\"TableNumber\" INTEGER, \"IsAYCE\" BOOLEAN)");
            statement.executeUpdate("CREATE TABLE \"Employees\" (\"Name\" VARCHAR(255), \"Role\" VARCHAR(255), \"Wage\" DOUBLE PRECISION)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testWriteToDatabase() {
        // Arrange
        String foodName = "Test Food";
        double price = 10.0;
        int quantity = 5;
        int selectedTableNumber = 1;

        // Act
        PostgreSQL.WriteToDatabase(foodName, price, quantity, selectedTableNumber);

        // Assert
        int rowCount = PostgreSQL.getRowCount(); // Retrieve from DB, should not be hardcoded
        String actualFoodName = PostgreSQL.readFoodNameFromDatabase(rowCount);
        double actualPrice = PostgreSQL.readPriceFromDatabase(rowCount);
        int actualQuantity = PostgreSQL.readQuantityFromDatabase(rowCount);
        int actualTableNumber = PostgreSQL.readTableNumberFromDataBase(rowCount);
        assertEquals(foodName, actualFoodName, "Expected and actual food names do not match!");
        assertEquals(price, actualPrice, "Expected and actual prices do not match!");
        assertEquals(quantity, actualQuantity, "Expected and actual quantities do not match!");
        assertEquals(selectedTableNumber, actualTableNumber, "Expected and actual table numbers do not match!");
    }

    @Test
    public void testWriteTableToDatabase_WithValidInput() {
        int selectedTableNumber = 8;
        boolean AYCE = true;



        // Write table to database
        PostgreSQL.WriteTableToDatabase(selectedTableNumber, AYCE);

        // Now check if the data is present
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String query = "SELECT \"IsAYCE\" FROM \"Tables\" WHERE \"TableNumber\" = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, selectedTableNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                boolean isAYCE = rs.getBoolean("IsAYCE");
                // Validate the inserted row
                Assertions.assertEquals(AYCE, isAYCE);
            }

        } catch (SQLException e) {
            Assertions.fail("Failed to execute SQL query: " + e.getMessage());
        }
    }

    @Test
    void testWriteToDatabase2() {
        /* Arrange */
        String foodName = "Burger";
        double price = 9.99;
        int quantity = 5;
        Integer selectedTableNumber = 1;

        /* Act */
        PostgreSQL.WriteToDatabase(foodName, price, quantity, selectedTableNumber);

        /* Assert */
        assertEquals(quantity, PostgreSQL.readQuantityFromDatabase(PostgreSQL.getRowCount()));
        assertEquals(price, PostgreSQL.readPriceFromDatabase(PostgreSQL.getRowCount()));
        assertEquals(foodName, PostgreSQL.readFoodNameFromDatabase(PostgreSQL.getRowCount()));
    }

    @Test
    void testWriteTableToDatabase() {
        /* Arrange */
        Integer selectedTableNumber = 1;
        boolean AYCE = true;

        /* Act */
        PostgreSQL.WriteTableToDatabase(selectedTableNumber, AYCE);

        /* Assert */
        assertTrue(PostgreSQL.isAYCE(selectedTableNumber));
    }

    @Test
    void testUpdateQuantity() {
        /* Arrange */
        String foodName = "Burger";
        int quantityToAdd = 2;
        PostgreSQL.WriteToDatabase(foodName, 9.99, 5, 1); // Assume this food already exists

        /* Act */
        PostgreSQL.updateQuantity(foodName, quantityToAdd);

        /* Assert */
        assertEquals(7, PostgreSQL.readQuantityFromDatabase(PostgreSQL.getRowCount())); // 5 + 2 = 7
    }

    @Test
    void testDeleteFood() {
        /* Arrange */
        String foodName = "Burger";
        PostgreSQL.WriteToDatabase(foodName, 9.99, 5, 1); // Assume this food already exists

        /* Act */
        PostgreSQL.deleteFood(foodName);

        /* Assert */
        assertNotEquals(foodName, PostgreSQL.readFoodNameFromDatabase(PostgreSQL.getRowCount()));
    }


    @Test
    void testReadFoodNameFromDatabase() {
        /* Arrange */
        String foodName = "Burger";
        double price = 9.99;
        int quantity = 5;
        Integer selectedTableNumber = 1;
        PostgreSQL.WriteToDatabase(foodName, price, quantity, selectedTableNumber);

        int rowNum = PostgreSQL.getRowCount(); // Get the most recently added row

        /* Act */
        String retrievedFoodName = PostgreSQL.readFoodNameFromDatabase(rowNum);

        /* Assert */
        assertEquals(foodName, retrievedFoodName);
    }

    @Test
    void testReadPriceFromDatabase() {
        /* Arrange */
        String foodName = "Burger";
        double price = 9.99;
        int quantity = 5;
        Integer selectedTableNumber = 1;
        PostgreSQL.WriteToDatabase(foodName, price, quantity, selectedTableNumber);

        int rowNum = PostgreSQL.getRowCount(); // Get the most recently added row

        /* Act */
        double retrievedPrice = PostgreSQL.readPriceFromDatabase(rowNum);

        /* Assert */
        assertEquals(price, retrievedPrice);
    }

    @Test
    void testReadQuantityFromDatabase() {
        /* Arrange */
        String foodName = "Burger";
        double price = 9.99;
        int quantity = 5;
        Integer selectedTableNumber = 1;
        PostgreSQL.WriteToDatabase(foodName, price, quantity, selectedTableNumber);

        int rowNum = PostgreSQL.getRowCount(); // Get the most recently added row

        /* Act */
        int retrievedQuantity = PostgreSQL.readQuantityFromDatabase(rowNum);

        /* Assert */
        assertEquals(quantity, retrievedQuantity);
    }

    @Test
    void testGetRowCount() {
        /* Arrange */
        PostgreSQL.WriteToDatabase("Burger", 9.99, 5, 1); // Assume these orders have distinct names
        PostgreSQL.WriteToDatabase("Pizza", 12.99, 7, 2);

        /* Act */
        int rowCount = PostgreSQL.getRowCount();

        /* Assert */
        assertEquals(2, rowCount); // As two orders are added, so row count should be 2
    }

    @Test
    void testMarkCompleted() {
        /* Arrange */
        PostgreSQL.WriteToDatabase("Burger", 9.99, 5, 1);
        int orderId = PostgreSQL.getRowCount(); // Assume orderId is same as row count

        /* Act */
        PostgreSQL.markCompleted("Burger");

        /* Assert */
        assertTrue(PostgreSQL.isOrderCompleted(orderId));
    }

    @Test
    void testIsOrderCompleted() {
        /* Arrange */
        PostgreSQL.WriteToDatabase("Burger", 9.99, 5, 1);
        int orderId = PostgreSQL.getRowCount(); // Assume orderId is same as row count
        PostgreSQL.markCompleted("Burger");

        /* Act & Assert */
        assertTrue(PostgreSQL.isOrderCompleted(orderId));
    }

    @Test
    void testReadTableNumberFromDataBase() {
        /* Arrange */
        Integer tableNumber = 1;
        PostgreSQL.WriteToDatabase("Burger", 9.99, 5, tableNumber);
        int rowNum = PostgreSQL.getRowCount();

        /* Act */
        int retrievedTableNumber = PostgreSQL.readTableNumberFromDataBase(rowNum);

        /* Assert */
        assertEquals(tableNumber, retrievedTableNumber);
    }

    @Test
    void testIsAYCE() {
        /* Arrange */
        Integer tableNumber = 1;
        boolean AYCE = true; // All You Can Eat (AYCE)
        PostgreSQL.WriteTableToDatabase(tableNumber, AYCE);

        /* Act */
        boolean isAYCE = PostgreSQL.isAYCE(tableNumber);

        /* Assert */
        assertTrue(isAYCE); // The table was marked as AYCE, so this should be true
    }

    @Test
    void testIsTableActive() {
        /* Arrange */
        Integer tableNumber = 1;
        boolean AYCE = true; // All You Can Eat (AYCE)
        PostgreSQL.WriteTableToDatabase(tableNumber, AYCE);

        /* Act */
        boolean isActive = PostgreSQL.isTableActive(tableNumber);

        /* Assert */
        assertTrue(isActive); // The table was just written to the database and so should be active
    }


    @Test
    void testUpdateEmployee() {
        /* Arrange */
        String originalName = "John Doe";
        String originalPosition = "Waiter";
        PostgreSQL.addEmployee(originalName, originalPosition, 10);

        String updatedName = "Jane Doe";
        String updatedPosition = "Manager";

        /* Act */
        PostgreSQL.updateEmployee(originalName, updatedName, updatedPosition, 15);

        /* Assert */
        List<String[]> employees = PostgreSQL.getAllEmployees();

        assertFalse(employees.contains(originalName));
    }

    @Test
    void testDeleteEmployee() {
        /* Arrange */
        String name = "John Doe";
        String position = "Waiter";
        PostgreSQL.addEmployee(name, position, 13);

        /* Act */
        PostgreSQL.deleteEmployee(name, position);

        /* Assert */
        List<String[]> employees = PostgreSQL.getAllEmployees();
        assertFalse(employees.contains(name));
    }



}
