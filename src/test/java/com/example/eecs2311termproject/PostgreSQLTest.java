package com.example.eecs2311termproject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PostgreSQLTest {

    private static final String url = "jdbc:postgresql:postgres";
    private static final String user = "postgres";
    private static final String password = "Adrian";

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
            statement.executeUpdate("CREATE TABLE \"Orders\" (\"Food name\" VARCHAR(255), \"Price\" DOUBLE PRECISION, \"Quantity\" INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testWriteToDatabase() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);

        // Verify that data was inserted successfully
        assertEquals(1, PostgreSQL.getRowCount());
    }

    @Test
    public void testUpdateQuantity() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);
        PostgreSQL.updateQuantity("Test Food", 1);

        // Verify that quantity was updated successfully
        assertEquals(3, PostgreSQL.readQuantityFromDatabase(1));
    }

    @Test
    public void testDeleteFood() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);
        PostgreSQL.deleteFood("Test Food");

        // Verify that food was deleted successfully
        assertEquals(0, PostgreSQL.getRowCount());
    }

    @Test
    public void testReadFoodNameFromDatabase() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);
        String foodName = PostgreSQL.readFoodNameFromDatabase(1);

        // Verify that the correct food name was read from the database
        assertEquals("Test Food", foodName);
    }

    @Test
    public void testReadPriceFromDatabase() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);
        double price = PostgreSQL.readPriceFromDatabase(1);

        // Verify that the correct price was read from the database
        assertEquals(10.99, price);
    }

    @Test
    public void testReadQuantityFromDatabase() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);
        int quantity = PostgreSQL.readQuantityFromDatabase(1);

        // Verify that the correct quantity was read from the database
        assertEquals(2, quantity);
    }

    @Test
    public void testGetRowCount() {
        PostgreSQL.WriteToDatabase("Test Food", 10.99, 2, selectedTableNumber);
        int rowCount = PostgreSQL.getRowCount();

        // Verify that the row count is correct
        assertEquals(1, rowCount);
    }
}
