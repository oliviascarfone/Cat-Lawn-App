package model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import persistance.Reader;
import persistance.Saveable;
import persistance.Writer;


import java.io.PrintWriter;
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
        } else {
            toys.add(item);
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

//    //EFFECTS: returns cat names in the lawn
//    public String catNames() {
//        String catNames = "";
//        for (int i = 0; i < cats.size(); i++) {
//            String catName = cats.get(i).getName();
//            if (i != (cats.size() - 1)) {
//                catNames += catName + ",";
//            } else {
//                catNames += catName;
//            }
//
//        }
//        return catNames;
//
//    }

//    //EFFECTS: returns food names in the lawn
//    public String foodNames() {
//        String foodNames = "";
//        for (int i = 0; i < food.size(); i++) {
//            String foodName = food.get(i).getName();
//            if (i != (food.size() - 1)) {
//                foodNames += foodName + ",";
//            } else {
//                foodNames += foodName;
//            }
//
//        }
//        return foodNames;
//    }

//    //EFFECTS: returns toy names in the lawn
//    public String toyNames() {
//        String toyNames = "";
//        for (int i = 0; i < toys.size(); i++) {
//            String toyName = toys.get(i).getName();
//            if (i != (toys.size() - 1)) {
//                toyNames += toyName + ",";
//            } else {
//                toyNames += toyName;
//            }
//        }
//        return toyNames;
//    }


    public JsonObject saveYard() {
        //saveCats();
        //saveFood();
        //saveToys();
        JsonObject yardJson = new JsonObject();
        yardJson.add("cats", saveCats());
        yardJson.add("food", saveFood());
        yardJson.add("toys", saveToys());

        return yardJson;


    }


    //EFFECTS: saves the cats in the yard into an array of JSON objects
    public JsonArray saveCats() {
        JsonArray saveCats = new JsonArray();
        for (Cat cat : cats) {
            Gson gson = new Gson();
            String saveCat = gson.toJson(cat);
            saveCats.add(saveCat);
        }

        return saveCats;
    }

    //EFFECTS: saves the food in the yard into an array of JSON objects
    public JsonArray saveFood() {
        JsonArray saveFoods = new JsonArray();
        for (Item food : food) {
            Gson gson = new Gson();
            String saveFood = gson.toJson(food);
            saveFoods.add(saveFood);

        }

        return saveFoods;
    }

    //EFFECTS: saves the toys in the yard into an array of JSON objects
    public JsonArray saveToys() {
        JsonArray saveToys = new JsonArray();
        for (Item toy : toys) {
            Gson gson = new Gson();
            String saveToy = gson.toJson(toy);
            saveToys.add(saveToy);

        }

        return saveToys;
    }





//    @Override
//    public void save(PrintWriter printWriter) {
//

//        printWriter.print(catNames());
//        //printWriter.print(Reader.DELIMITER);
//        printWriter.print(foodNames());
//        //printWriter.print(Reader.DELIMITER);
//        printWriter.print(toyNames());
//        //printWriter.print(Reader.DELIMITER);





}



