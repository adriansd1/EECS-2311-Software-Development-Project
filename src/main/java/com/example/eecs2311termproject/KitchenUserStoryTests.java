package com.example.eecs2311termproject;

import java.util.ArrayList;

public class KitchenUserStoryTests {

    public static void main(String[] args) {
        // Initialize system components
        ClientSide.clientOrder = new Order();
        Kitchen kitchen = new Kitchen();

        // Test 1: Basic order sending test
        testBasicOrderSending(kitchen);

        // Test 2: Order with items containing special dietary restrictions
        testSpecialDietaryRestrictions(kitchen);

        // Test 3: Order with items containing no special dietary restrictions
        testNoSpecialDietaryRestrictions(kitchen);
    }

    private static void testBasicOrderSending(Kitchen kitchen) {
        // Step 1: Add items to the order
        addItemToOrder("Sushi Roll", 1);
        addItemToOrder("Miso Soup", 2);
        addItemToOrder("Green Tea", 1);

        // Step 2: Send the order to the kitchen
        ArrayList<String> order = new ArrayList<>();
        order.add("Sushi Roll");
        order.add("Miso Soup");
        order.add("Green Tea");
        kitchen.recieveOrder(order);

        // Step 3: Verify order in the kitchen
        if (kitchen.sendOrder() != null && kitchen.sendOrder().size() > 0) {
            System.out.println("Order sent to the kitchen for preparation!");
        } else {
            System.out.println("Failed to send basic order to the kitchen!");
        }
    }

    private static void testSpecialDietaryRestrictions(Kitchen kitchen) {
        // Step 1: Add items to the order with special dietary restrictions
        addItemToOrder("Vegetarian Sushi Roll", 1);
        addItemToOrder("Gluten-free Miso Soup", 2);

        // Step 2: Send the order to the kitchen
        ArrayList<String> order = new ArrayList<>();
        order.add("Vegetarian Sushi Roll");
        order.add("Gluten-free Miso Soup");
        kitchen.recieveOrder(order);

        // Step 3: Verify order in the kitchen
        if (kitchen.sendOrder() != null && kitchen.sendOrder().size() > 0) {
            System.out.println("Order sent to the kitchen for preparation with special dietary restrictions!");
        } else {
            System.out.println("Failed to send order with special dietary restrictions to the kitchen!");
        }
    }

    private static void testNoSpecialDietaryRestrictions(Kitchen kitchen) {
        // Step 1: Add items to the order with no special dietary restrictions
        addItemToOrder("Sashimi", 3);
        addItemToOrder("Miso Soup", 1);

        // Step 2: Send the order to the kitchen
        ArrayList<String> order = new ArrayList<>();
        order.add("Sashimi");
        order.add("Miso Soup");
        kitchen.recieveOrder(order);

        // Step 3: Verify order in the kitchen
        if (kitchen.sendOrder() != null && kitchen.sendOrder().size() > 0) {
            System.out.println("Order sent to the kitchen for preparation!!");
        } else {
            System.out.println("Failed to send order with no special dietary restrictions to the kitchen!");
        }
    }

    private static void addItemToOrder(String itemName, int quantity) {
        Food item = new Food(itemName, 0.0); // Price doesn't matter for this test
        item.setQuantity(quantity);
        ClientSide.clientOrder.addFood(item);
    }
}
