/*package com.example.eecs2311termproject;

import com.example.eecs2311termproject.ClientSide;
import com.example.eecs2311termproject.Food;
import com.example.eecs2311termproject.Order;

import java.util.ArrayList;
public class KitchenUserStoryTests {
    public static void main(String[] args) {
        // Initialize variables
        ClientSide.clientOrder = new Order();
        Kitchen kitchen = new Kitchen();
        // Test 1: Sending a basic order
        testBasicOrderSending(kitchen);
        // Test 2: Sending an order with dietary restrictions
        testSpecialDietaryRestrictions(kitchen);
        // Test 3: Sending an order without dietary restriction
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
            System.out.println("Order successfully sent to the kitchen!, getting prepared now thank you for your patience");
        } else {
            System.out.println("Order was not sent to the kitchen");
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
            System.out.println("Order with special dietary restrictions sent to the kitchen!, getting prepared now thank you for your patience");
        } else {
            System.out.println("Order was not sent to the kitchen");
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
            System.out.println("Order with no special dietary restrictions sent to the kitchen!, getting prepared now thank you for your patience");
        } else {
            System.out.println("Order was not sent to the kitchen");
        }
    }
    private static void addItemToOrder(String itemName, int quantity) {
        Food item = new Food(itemName, 0.0); // Price doesn't matter for this test
        item.setQuantity(quantity);
        ClientSide.clientOrder.addFood(item);
    }
}
*/