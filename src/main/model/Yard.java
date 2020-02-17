package model;


import java.util.ArrayList;




//Represents a backyard that contains cats and placed items
//Used ideas from A2 - Grocery Bill lab
public class Yard {
    public ArrayList<Cat> cats;
    public ArrayList<Item> food;
    public ArrayList<Item> toys;
    GameCats gameCats = new GameCats();
    int newFoodTracker;
    int newToyTracker;



    //EFFECTS: constructs a yard instance with no cats or items.
    public Yard() {
        cats = new ArrayList<>();
        food = new ArrayList<>();
        toys = new ArrayList<>();
        gameCats.listCommonCats();
        gameCats.listUncommonCats();




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
    //MODIFIES: this
    //EFFECTS: adds an item from the inventory to the yard
    public void addItemToYard(Item item) {
        if (item.getClass() == Food.class) {
            food.add(item);
            newFoodTracker++;
        } else {
            toys.add(item);
            newToyTracker++;
        }


    }




    //EFFECTS: returns a message containing the names of all items currently in the yard.
    public String itemsInYard(ArrayList<Item> itemType) {
        String itemsToString = "";
        if (itemType.size() > 0) {
            for (int i = 0; i < itemType.size(); i++) {
                String eachItem = itemType.get(i).getName();

                itemsToString += (String.format("You have placed %s in your yard!\n",
                        eachItem));

            }
            return itemsToString;
        } else {
            itemsToString += "There are none placed in your yard!";
            return itemsToString;
        }
    }

    //REQUIRES: GameCats lists are not empty
    //MODIFIES: this
    //EFFECTS: adds a cat to the yard, either common or uncommon cat depending on the item placed
    public void addCatToYard() {
        Cat cc;
        Cat uc;
        if (food.size() >= 1) {
            cc = gameCats.generateCommonCat();
            if ((!cats.contains(cc)) && (food.size() > cats.size())) {
                cats.add(cc);
            }
        }
        if (toys.size() >= 1) {
            uc = gameCats.generateUncommonCat();
            if ((!cats.contains(uc)) && (toys.size() >= cats.size())) {
                cats.add(uc);
            }
        }
    }


}



