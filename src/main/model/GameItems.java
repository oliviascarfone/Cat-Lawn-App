package model;

import java.util.ArrayList;

//represents all available items in the game
public class GameItems {
    public ArrayList<Food> allFood;
    public ArrayList<Toy> allToys;


    //EFFECTS: creates GameItems instance
    public GameItems() {
        allFood = new ArrayList<>();
        allToys = new ArrayList<>();
        listFood();
        listToys();
    }


    //MODIFIES: this
    //EFFECTS: creates list of all food items in the game
    public void listFood() {
        Food kibble = new Food("Kibble", 0);
        Food tuna = new Food("Tuna", 1);
        Food salmon = new Food("Salmon", 2);
        allFood.add(kibble);
        allFood.add(tuna);
        allFood.add(salmon);

    }

    //MODIFIES: this
    //EFFECTS: creates list of all toy items in the game
    public void listToys() {
        Toy spring = new Toy("Spring", 0);
        Toy mouse = new Toy("Mouse", 1);
        Toy brush = new Toy("Brush", 2);
        allToys.add(spring);
        allToys.add(mouse);
        allToys.add(brush);


    }

    //EFFECTS: returns all Food Item names and cost
    public String showAllFood() {
        String foodToString = "";
        for (int i = 0; i < allFood.size(); i++) {
            String eachItem = allFood.get(i).getName();
            int eachItemCost = allFood.get(i).getCost();
            foodToString += (String.format(" %s for $%s\n",
                    eachItem, eachItemCost));

        }
        return foodToString;
    }

    //EFFECTS: returns all Toy Item names and cost
    public String showAllToys() {
        String toyToString = "";
        for (int i = 0; i < allToys.size(); i++) {
            String eachItem = allToys.get(i).getName();
            int eachItemCost = allToys.get(i).getCost();
            toyToString += (String.format(" %s for $%s\n",
                    eachItem, eachItemCost));

        }
        return toyToString;
    }



}

