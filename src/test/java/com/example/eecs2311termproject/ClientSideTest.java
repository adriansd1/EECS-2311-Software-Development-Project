package com.example.eecs2311termproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ClientSideTest {


    public void start(Stage stage) throws Exception {
        ClientSide clientSide = new ClientSide();
        clientSide.start(stage);
    }

    @Test
    void testStart() {
        //start() method is called automatically
    }

    @Test
    void testMenuItem() {
        MenuItem menuItem = new MenuItem("Test");
        assertNotNull(menuItem);
        assertEquals("Test", menuItem.getText());
    }

    @Test
    void testButtonClick() {
        // Create a new JavaFX thread for UI interactions
        Platform.startup(() -> {
            // Create a button
            Button button = new Button("Test");

            // Create a flag to track if the button was clicked
            AtomicBoolean clicked = new AtomicBoolean(false);

            // Add an action listener to the button
            button.setOnAction(e -> clicked.set(true));

            // Simulate a button click
            button.fire();

            // Verify that the button was clicked
            assertTrue(clicked.get());
        });
    }


    @Test
    void testBorderPane() {
        BorderPane borderPane = new BorderPane();
        assertNotNull(borderPane);
    }

    @Test
    void testVBox() {
        VBox vBox = new VBox();
        assertNotNull(vBox);
    }
}
