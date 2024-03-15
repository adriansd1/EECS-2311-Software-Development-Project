package com.example.eecs2311termproject;
/*
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class KitchenTest {

    private Kitchen kitchen;
    private ArrayList<String> sampleOrder;

    @BeforeEach
    public void setUp() {
        kitchen = new Kitchen();
        sampleOrder = new ArrayList<>();
        sampleOrder.add("rice");
        sampleOrder.add("fish");
    }

    @Test
    public void testSendOrder() {
        kitchen.recieveOrder(sampleOrder);
        assertEquals(sampleOrder, kitchen.sendOrder());
    }

    @Test
    public void testReceiveOrder() {
        kitchen.recieveOrder(sampleOrder);
        assertNotNull(kitchen.sendOrder());
    }

    @Test
    public void testCheckIngredientsSuccess() {
        // Assuming the kitchen has rice and fish in stock
        kitchen.ingredientStock = new Food[2];
        //kitchen.ingredientStock[0] = new Food();
        kitchen.ingredientStock[0].setIngredients(new ArrayList<String>() {
            {
                add("rice");
            }
        }
        );
        //kitchen.ingredientStock[1] = new Food();
        kitchen.ingredientStock[1].setIngredients(new ArrayList<String>() {{ add("fish"); }});

        assertTrue(kitchen.checkingredients());
    }

    @Test
    public void testCheckIngredientsFailure() {
        // Assuming the kitchen has rice but not fish in stock
        kitchen.ingredientStock = new Food[1];
        //kitchen.ingredientStock[0] = new Food();
        kitchen.ingredientStock[0].setIngredients(new ArrayList<String>() {{ add("rice"); }});

        assertFalse(kitchen.checkingredients());
    }

    @Test
    public void testCookRice() {
        // Not much to test here, just check if it runs without error
        kitchen.cookrice();
    }

    @Test
    public void testSliceFish() {
        // Not much to test here, just check if it runs without error
        kitchen.slicefish();
    }
}
*/