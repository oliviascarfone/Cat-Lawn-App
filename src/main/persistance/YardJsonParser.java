package persistance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.Cat;
import model.Food;
import model.Item;
import model.Toy;
import ui.CatLawnApp;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class YardJsonParser {
    JsonParser parser;
    private static final String YARD_FILE = "./data/yard.json";
    private CatLawnApp catLawn;


    public YardJsonParser(CatLawnApp catLawn) {
        this.catLawn = catLawn;
    }

    public void loadYard() {
        parser = new JsonParser();
        try (FileReader reader = new FileReader(YARD_FILE)) {
            JsonObject result = parser.parse(reader).getAsJsonObject();
            JsonArray loadedCats = result.getAsJsonArray("cats");
            ArrayList<Cat> listOfCats = makeCatsList(loadedCats);
            JsonArray loadedFood = result.getAsJsonArray("food");
            ArrayList<Item> listOfFood = makeFoodList(loadedFood);
            JsonArray loadedToys = result.getAsJsonArray("toys");
            ArrayList<Item> listOfToy = makeToyList(loadedToys);
            catLawn.makeYard(listOfCats, listOfFood, listOfToy);

        } catch (IOException e) {
            catLawn.emptyYard();
        }
    }

    public ArrayList<Cat> makeCatsList(JsonArray json) {
        Gson gson = new Gson();
        Type catListType = new TypeToken<ArrayList<Cat>>(){}.getType();
        ArrayList<Cat> cats = gson.fromJson(json, catListType);
        return cats;
    }

    public ArrayList<Item> makeFoodList(JsonArray json) {
        Gson gson = new Gson();
        Type foodListType = new TypeToken<ArrayList<Food>>(){}.getType();
        ArrayList<Item> foods = gson.fromJson(json, foodListType);
        return foods;
    }

    public ArrayList<Item> makeToyList(JsonArray json) {
        Gson gson = new Gson();
        Type toyListType = new TypeToken<ArrayList<Toy>>(){}.getType();
        ArrayList<Item> toys = gson.fromJson(json, toyListType);
        return toys;
    }









}
