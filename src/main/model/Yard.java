package model;


import java.util.ArrayList;

//Represents a backyard that contains cats and placed items
//Used ideas from A2 - Grocery Bill lab
public class Yard {
    public ArrayList<Cat> cats;
    public ArrayList<Item> food;
    public ArrayList<Item> toys;

    //EFFECTS: constructs a yard instance with no cats or items
    public Yard() {
        cats = new ArrayList<>();
        food = new ArrayList<>();
        toys = new ArrayList<>();

    }


    //EFFECTS: returns a message containing the names of all cats currently in the yard.
    public String toString() {
        String catsToString = "";
        if (cats.size() > 0) {
            for (int i = 0; i < cats.size(); i++) {
                String eachCat = cats.get(i).getName();

                catsToString += (String.format("%s has visited your lawn!\n",
                        eachCat));

            }
            return catsToString;
        } else {
            catsToString += "There are no cats in your yard!";
            return catsToString;
        }
    }


    //REQUIRES: Must have a non-empty Inventory
    //MODIFIES: this, Inventory
    //EFFECTS: adds an item from the inventory to the yard
    public void addItemToYard(Item item) {



    }


}



