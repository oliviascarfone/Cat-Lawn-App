package model;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.util.ArrayList;




//Represents a backyard that contains cats and placed items
//Used ideas from A2 - Grocery Bill lab
public class Yard  {
    public ArrayList<Cat> cats;
    public ArrayList<Item> food;
    public ArrayList<Item> toys;
    GameCats gameCats = new GameCats();


    //EFFECTS: constructs a yard instance with no cats or items.
    public Yard() {
        cats = new ArrayList<>();
        food = new ArrayList<>();
        toys = new ArrayList<>();
        gameCats.listCommonCats();
        gameCats.listUncommonCats();


    }

    //EFFECTS: Constructs a yard from saved yard data
    public Yard(ArrayList<Cat> cats, ArrayList<Item> food, ArrayList<Item> toys) {
        this.cats = cats;
        this.food = food;
        this.toys = toys;
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

    public ArrayList<String> catsInYardArray() {
        ArrayList<String> catsInArray = new ArrayList<>();
        String catToString = "";
        if (cats.size() > 0) {
            for (int i = 0; i < cats.size(); i++) {
                String eachCat = cats.get(i).getName();
                catToString = (String.format("%s",
                        eachCat));
                catsInArray.add(catToString);
            }
        }
        return catsInArray;
    }

    //REQUIRES: Must have a non-empty Inventory
    //MODIFIES: this
    //EFFECTS: adds an item from the inventory to the yard
    public void addItemToYard(String selection) { //old sign was Item item
        if (selection.equals("Kibble")) {
            food.add(new Food("Kibble", 0));
        } else if (selection.equals("Spring")) {
            toys.add(new Toy("Spring", 0));
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
        Cat cc = gameCats.generateCommonCat();
        Cat uc = gameCats.generateUncommonCat();
        if (food.size() >= 1) {
            if ((!cats.contains(cc)) && (food.size() > cats.size())) {
                cats.add(cc);
            }
        }
        if (toys.size() >= 1) {
            if ((!cats.contains(uc)) && (toys.size() >= cats.size())) {
                cats.add(uc);

            }
        }
    }

    public void clearYard(Yard y) {
        y.cats.clear();
        y.toys.clear();
        y.food.clear();
    }

    public String getCatPic(String selection) {
        return gameCats.retrieveCatPath(selection);

    }






}



