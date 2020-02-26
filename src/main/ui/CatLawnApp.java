package ui;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.*;
import persistance.JsonWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.Type;

//ideas taken from AccountNotRobust program
//https://futurestud.io/tutorials/gson-mapping-of-nested-objects - this and the other gson tutorials
// I used for Phase 2
//https://www.leveluplunch.com/java/examples/convert-json-array-to-arraylist-gson/
public class CatLawnApp {
    private static final String YARD_FILE = "./data/yard.json";
    private static final String INVENTORY_FILE = "./data/inventory.json";
    boolean keepGoing = true;
    String command = null;
    Scanner input;
    Yard yard = new Yard();
    Inventory inventory = new Inventory();
    GameItems gameItems = new GameItems();
    GameCats gameCats = new GameCats();
    JsonObject yardJson;
    JsonObject inventoryJson;
    ArrayList<Cat> cats;
    ArrayList<Item> foods;
    ArrayList<Item> toys;


    public CatLawnApp() {
        runCatLawnApp();
        gameItems.listToys();
        gameItems.listFood();
        gameCats.listCommonCats();
        gameCats.listUncommonCats();
    }

    //EFFECTS: processes user input
    private void runCatLawnApp() {
        input = new Scanner(System.in);
        command = input.nextLine();

        loadGame();


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcats -> see the cats in my yard");
        System.out.println("\tyard -> see the items in my yard");
        System.out.println("\tinventory -> check my inventory");
        System.out.println("\tplace -> place items in yard");
        System.out.println("\tshop -> shop for items");
        System.out.println("\tsave -> save your game");
        System.out.println("\tquit -> quit the game");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("cats")) {
            yard.addCatToYard();
            System.out.println(yard.catsInYard());
        } else if (command.equals("yard")) {
            showItemsInYard();
        } else if (command.equals("inventory")) {
            System.out.println(inventory.checkInventoryItems());
            System.out.println("You have $" + inventory.getBalance());
        } else if (command.equals("place")) {
            placeItemsInYard();
        } else if (command.equals("shop")) {
            shopItems();
        } else if (command.equals("save")) {
            JsonWriter.saveGame(yard);
        } else {
            System.out.println("Invalid selection, please try another menu option");
        }
    }


    //EFFECTS: places items from the inventory into the yard
    private void placeItemsInYard() {
        String selectedItemName;
        Item selectedItem;
        if (inventory.inventoryList.size() == 0) {
            System.out.println("You have nothing to place! Try buying something first");
            return;
        } else if (inventory.inventoryList.size() > 0) {
            System.out.println("type the name of the item you want to place");
            Scanner newscan = new Scanner(System.in);
            String option = newscan.nextLine();
            for (int i = 0; inventory.inventoryList.size() > i; i++) {
                selectedItem = inventory.inventoryList.get(i).getItem();
                selectedItemName = inventory.inventoryList.get(i).getItem().getName();
                if (selectedItemName.equals(option)) {
                    inventory.removeItemFromInventory(selectedItem);
                    System.out.println("placed " + selectedItemName);
                    yard.addItemToYard(selectedItem);
                    return;
                }
            }
            System.out.println("item not found, please check inventory and try again");
        }

    }

    //MODIFIES: Inventory
    //EFFECTS: purchase an item and add it to inventory
    public void shopItems() {
        String choice = "";

        System.out.println("Would you like to purchase food or toys?");
        while (!(choice.equals("food")) || (choice.equals("toys"))) {
            System.out.println("\tfood-> buy food");
            System.out.println("\ttoys-> buy toys");
            choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("food")) {
                buyFood();
                return;
            }
            if (choice.equals("toys")) {
                buyToys();
                return;
            }
        }
    }

    public void buyFood() {
        Food selectedFoodToPurchase;
        String selectedFoodToPurchaseName;
        String choice;
        System.out.println(gameItems.showAllFood());
        System.out.println("What would you like to purchase?");
        Scanner input = new Scanner(System.in);
        choice = input.nextLine();
        for (int i = 0; gameItems.allFood.size() > i; i++) {
            selectedFoodToPurchase = gameItems.allFood.get(i);
            selectedFoodToPurchaseName = gameItems.allFood.get(i).getName();
            if (selectedFoodToPurchaseName.equals(choice)) {
                if (inventory.buyItem(selectedFoodToPurchase, 1)) {
                    System.out.println("Purchase Successful!");
                    return;
                } else {
                    System.out.println("Insufficient funds!");
                    return;
                }
            }
        }
        System.out.println("Item not found, please try again");

    }


    public void buyToys() {
        Toy selectedToyToPurchase;
        String selectedToyToPurchaseName;
        String choice;
        System.out.println(gameItems.showAllToys());
        System.out.println("What would you like to purchase?");
        Scanner input = new Scanner(System.in);
        choice = input.nextLine();
        for (int i = 0; gameItems.allToys.size() > i; i++) {
            selectedToyToPurchase = gameItems.allToys.get(i);
            selectedToyToPurchaseName = gameItems.allToys.get(i).getName();
            if (selectedToyToPurchaseName.equals(choice)) {
                if (inventory.buyItem(selectedToyToPurchase, 1)) {
                    System.out.println("Purchase Successful!");
                    return;
                } else {
                    System.out.println("Insufficient funds!");
                    return;
                }
            }

        }
        System.out.println("Item not found, please try again");

    }


    //EFFECTS: shows the current items placed in the yard
    public void showItemsInYard() {
        String selection = "";

        while (!(selection.equals("f") || selection.equals("t"))) {
            System.out.println("\tf-> show food");
            System.out.println("\tt-> show toys");
            selection = input.next();
            selection = selection.toLowerCase();

            if (selection.equals("f")) {
                System.out.println(yard.itemsInYard(yard.food));
            } else {
                System.out.println(yard.itemsInYard(yard.toys));
            }
            return;
        }
    }


//    //EFFECTS: saves the state of the Cat Lawn Yard and Inventory to YARD_FILE and INVENTORY_FILE,
//    //         respectively
//    private void saveGame() {
//        JsonObject jsonObjectYard = saveYard();
//        //JsonObject jsonObjectInventory = saveInventory();
//        try {
//            FileWriter fileWriter = new FileWriter(YARD_FILE);
//            fileWriter.write(jsonObjectYard.toString());
//            fileWriter.close();
//            System.out.println("Successfully saved yard data!");
////            FileWriter fileWriter1 = new FileWriter(INVENTORY_FILE);
////            fileWriter1.write(jsonObjectInventory.toString());
////            fileWriter1.close();
//            System.out.println("Successfully saved inventory data!");
//        } catch (FileNotFoundException e) {
//            System.out.println("Problem saving game data");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public JsonObject saveYard() {
//        yardJson = new JsonObject();
//        yardJson.add("cats", saveCats());
//        yardJson.add("food", saveFood());
//        yardJson.add("toys", saveToys());
//
//        return yardJson;
//    }

//    public JsonObject saveInventory() {
//        inventoryJson = new JsonObject();
//        inventoryJson.addProperty("balance", inventory.getBalance());
//        inventoryJson.add("inventoryList", saveInventoryList());
//
//        return inventoryJson;
//    }

//    public JsonArray saveInventoryList() {
//        JsonArray saveInventoryList = new JsonArray();
//        for (InventoryEntry i : inventory.inventoryList) {
//            Gson gson = new Gson();
//            String saveInventoryEntry = gson.toJson(i);
//            saveInventoryList.add(saveInventoryEntry);
//        }
//        return saveInventoryList;
//    }
//
//
//    //EFFECTS: saves the cats in the yard into an array of JSON objects
//    public JsonArray saveCats() {
//        JsonArray saveCats = new JsonArray();
//        for (Cat cat : yard.cats) {
//            JsonObject catJson = new JsonObject();
//            catJson.addProperty("name", cat.getName());
//            catJson.addProperty("breed", cat.getBreed());
//            catJson.addProperty("coat", cat.getCoat());
//            catJson.addProperty("rarity", cat.getRarityLevel());
//            catJson.addProperty("foodpref", cat.getFoodPreference());
//            catJson.addProperty("toypref", cat.getToyPreference());
//            saveCats.add(catJson);
//        }
//
//        return saveCats;
//    }
//
//    //EFFECTS: saves the food in the yard into an array of JSON objects
//    public JsonArray saveFood() {
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
//    //EFFECTS: saves the toys in the yard into an array of JSON objects
//    public JsonArray saveToys() {
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


    public void loadGame() {
        loadYard();
    }
        //loadInventory();


    public void loadYard() {
        JsonParser parser = new JsonParser();
        try (FileReader reader = new FileReader(YARD_FILE)) {
            JsonObject result = parser.parse(reader).getAsJsonObject();
            JsonArray loadedCats = result.getAsJsonArray("cats");
            makeCatsList(loadedCats);
            JsonArray loadedFood = result.getAsJsonArray("food");
            makeFoodList(loadedFood);
            JsonArray loadedToys = result.getAsJsonArray("toys");
            makeToyList(loadedToys);
            makeYard();

        } catch (IOException e) {
            emptyYard();
        }
    }

    public void makeCatsList(JsonArray json) {
        Gson gson = new Gson();
        Type catListType = new TypeToken<ArrayList<Cat>>(){}.getType();
        ArrayList<Cat> cats = gson.fromJson(json, catListType);
        this.cats = cats;
    }

    public void makeFoodList(JsonArray json) {
        Gson gson = new Gson();
        Type foodListType = new TypeToken<ArrayList<Food>>(){}.getType();
        ArrayList<Item> foods = gson.fromJson(json, foodListType);
        this.foods = foods;
    }

    public void makeToyList(JsonArray json) {
        Gson gson = new Gson();
        Type toyListType = new TypeToken<ArrayList<Toy>>(){}.getType();
        ArrayList<Item> toys = gson.fromJson(json, toyListType);
        this.toys = toys;
    }

    public void makeYard(ArrayList<Cat> cats, ArrayList<Item> foods, ArrayList<Item> toys) {
        yard = new Yard(cats, foods, toys);

    }




//    public void loadInventory() {
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(INVENTORY_FILE)) {
//            Inventory loadedInventoryList = gson.fromJson(reader, Inventory.class);
//            inventory = loadedInventoryList;
//        } catch (IOException e) {
//            emptyInventory();
//        }
//
//    }
//
//    public void emptyInventory() {
//        inventory = new Inventory();
//    }

    public void emptyYard() {
        yard = new Yard();
    }

}










