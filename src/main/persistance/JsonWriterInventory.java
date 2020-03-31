package persistance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.Cat;
import model.Inventory;
import model.Item;
import model.Yard;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriterInventory {

    private static Gson inventoryJson;

    JsonWriterInventory() {

    }


    //EFFECTS: saves the same Inventory using GSON to file
    public static boolean saveGame(Inventory inventory, String inventoryFile) {
        String jsonObjectInventory = saveInventory(inventory);
        try {
            FileWriter fileWriter = new FileWriter(inventoryFile);
            fileWriter.write(jsonObjectInventory);
            fileWriter.close();
            System.out.println("Successfully saved game data!");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Problem saving game data");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String saveInventory(Inventory inventory) {
        inventoryJson = new Gson();
        String savedInventoryString = inventoryJson.toJson(inventory);
        return savedInventoryString;
    }


}
