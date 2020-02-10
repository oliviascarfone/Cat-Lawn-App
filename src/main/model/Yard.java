package model;


import java.util.ArrayList;

//Represents a backyard that contains cats and placed items
//Used ideas from A2 - Grocery Bill lab
public class Yard {
    public ArrayList<Cat> cats;
    public ArrayList<Item> items;


    //EFFECTS: constructs a yard instance with no cats or items
    public Yard() {
        cats = new ArrayList<>();
        items = new ArrayList<>();


    }


    //EFFECTS: returns a message containing the names of all cats currently in the yard.
    public String catsInYard() {
        String catsToString = "";
        if (cats.size() > 0) {
            for (int i = 0; i < cats.size(); i++) {
                String eachCat = cats.get(i).getName();

                catsToString += (String.format("%s is visiting your lawn!\n",
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
        items.add(item);

    }




    //EFFECTS: returns a message containing the names of all items currently in the yard.
    public String itemsInYard() {
        String itemsToString = "";
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                String eachItem = items.get(i).getName();

                itemsToString += (String.format("You have placed  %s in your lawn!\n",
                        eachItem));

            }
            return itemsToString;
        } else {
            itemsToString += "There are no items in your yard!";
            return itemsToString;
        }
    }

    public void addCatToYard(Item item) {

    }


}



