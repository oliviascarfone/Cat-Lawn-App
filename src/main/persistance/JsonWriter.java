//package persistance;
//
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import model.Cat;
//import model.Inventory;
//import model.Item;
//import model.Yard;
//import netscape.javascript.JSObject;
//
//import java.io.*;
//import java.util.ArrayList;
//
////ideas from https://crunchify.com/how-to-write-json-object-to-file-in-java/
////a writer that writes cat lawn data to a file
//public class JsonWriter {
//    //private static final String YARD_FILE = "./data/yard.json";
//    //private static final String INVENTORY_FILE = "./data/inventory.json";
//    private static JsonObject yardJson;
//    private static Gson inventoryJson;
//
//
//    //EFFECTS: creates new Json Writer
//    public JsonWriter(Yard yard, Inventory inventory, String yardFile, String inventoryFile) {
//        saveGame(yard, inventory, yardFile, inventoryFile);
//
//    }
//
//
//    //MODIFIES: .json file
//    //EFFECTS: saves the state of the Cat Lawn Yard to YARD_FILE and INVENTORY_FILE as a JSON object
//    public static boolean saveGame(Yard yard, Inventory inventory, String yardFile, String inventoryFile) {
//        JsonWriterInventory.saveGame(inventory, inventoryFile);
//        JsonWriterYard.saveGame(yard, yardFile);
//        return true;
//    }
////        JsonObject jsonObjectYard = saveYard(yard);
////        String jsonObjectInventory = saveInventory(inventory);
////        try {
////            FileWriter fileWriter = new FileWriter(yardFile);
////            fileWriter.write(jsonObjectYard.toString());
////            //fileWriter.write(jsonObjectInventory);
////            fileWriter.close();
////            FileWriter fileWriter2 = new FileWriter(inventoryFile);
////            fileWriter2.write(jsonObjectInventory);
////            fileWriter2.close();
////            System.out.println("Successfully saved game data!");
////            return true;
////        } catch (FileNotFoundException e) {
////            System.out.println("Problem saving game data");
////            return false;
////        } catch (IOException e) {
////            e.printStackTrace();
////            return false;
////        }
//
//
//
//
//
//
//    //MODIFIES: this
//    //EFFECTS: constructs the JSON object out of the components of Yard JSON elements
//    public static JsonObject saveYard(Yard yard) {
//        yardJson = new JsonObject();
//        yardJson.add("cats", saveCats(yard));
//        yardJson.add("food", saveFood(yard));
//        yardJson.add("toys", saveToys(yard));
//
//        return yardJson;
//    }
//
//    public static String saveInventory(Inventory inventory) {
//        inventoryJson = new Gson();
//        String savedInventoryString = inventoryJson.toJson(inventory);
//        return savedInventoryString;
//    }
//
//    //EFFECTS: saves the cats in the yard into an Array
//    public static JsonArray saveCats(Yard yard) {
//        JsonArray saveCats = new JsonArray();
//        for (Cat cat : yard.cats) {
//            JsonObject catJson = new JsonObject();
//            catJson.addProperty("name", cat.getName());
//            catJson.addProperty("breed", cat.getBreed());
//            catJson.addProperty("coat", cat.getCoat());
//            catJson.addProperty("rarityLevel", cat.getRarityLevel());
//            catJson.addProperty("foodPreference", cat.getFoodPreference());
//            catJson.addProperty("toyPreference", cat.getToyPreference());
//            saveCats.add(catJson);
//        }
//
//        return saveCats;
//    }
//
//    //EFFECTS: saves the food in the yard into an Array
//    public static JsonArray saveFood(Yard yard) {
//        JsonArray saveFoods = new JsonArray();
//        for (Item food : yard.food) {
//            JsonObject foodJson = new JsonObject();
//            foodJson.addProperty("name", food.getName());
//            foodJson.addProperty("cost", food.getCost());
//            saveFoods.add(foodJson);
//        }
//
//        return saveFoods;
//    }
//
//    //EFFECTS: saves the toys in the yard into an Array
//    public static JsonArray saveToys(Yard yard) {
//        JsonArray saveToys = new JsonArray();
//        for (Item toy : yard.toys) {
//            JsonObject toyJson = new JsonObject();
//            toyJson.addProperty("name", toy.getName());
//            toyJson.addProperty("cost", toy.getCost());
//            saveToys.add(toyJson);
//
//        }
//
//        return saveToys;
//    }
//
//
//}
//
//
//
