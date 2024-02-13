package com.example.eecs2311termproject;
import java.util.ArrayList;
//Why does this extend class, needs GUI follow client side to define functionality
public class Kitchen {//extends Food {
    ArrayList<Food> ingredientsStock;
    ArrayList<String> order;
    Boolean orderstate;
    private Food[] ingredientStock;

    //Commented out as it causes errors and makes code unrunnable
    //public Kitchen(int calories, double protein, double carbs, double fat, ArrayList<String> ingredients, ArrayList<String> dietaryRestrictions, ArrayList<String> allergies) {
        //super(calories, protein, carbs, fat, ingredients, dietaryRestrictions, allergies);
    //}

    public ArrayList<String> sendOrder() {
        return order;
    }

    public void recieveOrder(ArrayList<String> orders) {
        this.order = orders;
    }

    public boolean checkingredients() {
        for (String ingredient : Food.getIngredients()) {
            boolean ingredientfound = false;
            for (Food stockIngredient : ingredientStock) {
                if (stockIngredient.getIngredients().contains(ingredient)) {
                    ingredientfound = true;
                    break;
                }else if (!(stockIngredient.getIngredients().contains(ingredient))) {
                    return false;
                }
                return true;
        }
    }
        return true; //Prevent errors
    }
    public void cookrice(){
        System.out.print("Cooking rice");
        System.out.print("Rice ready!");
    }
    public void slicefish(){
        System.out.print("Slicing fish");
        System.out.print("Fish Ready!");

    }
    public void handleSpecialRequests(Order order) {
        String specialRequests = order.getSpecialRequests();
        if (!specialRequests.isEmpty()) {
            System.out.println("Handling special requests for order: " + order);
            // Your implementation to handle each special request
            }
        else{
            System.out.println("No special requests for order: " + order);
        }
    }
}





