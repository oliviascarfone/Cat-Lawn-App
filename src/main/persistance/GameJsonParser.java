package persistance;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.*;
import ui.Gui;


import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

//class representing a Json Parser to read saved files
//https://futurestud.io/tutorials/gson-mapping-of-nested-objects - this and the other gson tutorials
// I used for Phase 2
//https://www.leveluplunch.com/java/examples/convert-json-array-to-arraylist-gson/
public class GameJsonParser {
    JsonParser parser;
    //private static final String YARD_FILE = "./data/yard.json";
    private Gui gui;


    //EFFECTS; creates new YardJsonParser
    public GameJsonParser(Gui gui) {
        this.gui = gui;
    }

    //MODIFIES: CatLawn
    //EFFECTS: loads data from .json file
    public boolean loadGame(String yardFile, String inventoryFile) {
        Gson parser = new Gson();
        Gson parser2 = new Gson();

        try (FileReader reader = new FileReader(yardFile)) {
            JsonObject result = parser.fromJson(reader, JsonObject.class);  // .(reader).getAsJsonObject();
            JsonArray loadedCats = result.getAsJsonArray("cats");
            ArrayList<Cat> listOfCats = makeCatsList(loadedCats);
            JsonArray loadedFood = result.getAsJsonArray("food");
            ArrayList<Item> listOfFood = makeFoodList(loadedFood);
            JsonArray loadedToys = result.getAsJsonArray("toys");
            ArrayList<Item> listOfToy = makeToyList(loadedToys);
            gui.loadedGameYard(listOfCats, listOfFood, listOfToy);

            try (FileReader reader2 = new FileReader(inventoryFile)) {
                Inventory inventoryFromFile = parser2.fromJson(reader2, Inventory.class);
                //Inventory inventoryFromFile = parser2.fromJson(String.valueOf(inventoryString), Inventory.class);
                gui.loadedGameInventory(inventoryFromFile);
            }

            return true;

        } catch (IOException e) {
            gui.emptyYard();
            gui.emptyInventory();
            return false;
        }
    }


    //EFFECTS: Makes an ArrayList of Cat Objects from JSON file
    public ArrayList<Cat> makeCatsList(JsonArray json) {
        Gson gson = new Gson();
        Type catListType = new TypeToken<ArrayList<Cat>>() {
        }.getType();
        ArrayList<Cat> cats = gson.fromJson(json, catListType);
        return cats;
    }

    //EFFECTS: Makes an ArrayList of Food Objects from JSON file
    public ArrayList<Item> makeFoodList(JsonArray json) {
        Gson gson = new Gson();
        Type foodListType = new TypeToken<ArrayList<Food>>() {
        }.getType();
        ArrayList<Item> foods = gson.fromJson(json, foodListType);
        return foods;
    }

    //EFFECTS: Makes an ArrayList of Toy Objects from JSON file
    public ArrayList<Item> makeToyList(JsonArray json) {
        Gson gson = new Gson();
        Type toyListType = new TypeToken<ArrayList<Toy>>() {
        }.getType();
        ArrayList<Item> toys = gson.fromJson(json, toyListType);
        return toys;
    }
}










