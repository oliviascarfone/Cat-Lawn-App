package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Preconditions;
import model.*;
import persistance.Reader;
import persistance.Saveable;
import persistance.Writer;
import persistance.YardJsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ideas taken from AccountNotRobust program
//https://futurestud.io/tutorials/gson-mapping-of-nested-objects - this and the other gson tutorials
// I used for Phase 2
public class CatLawnApp {
    private static final String YARD_FILE = "./data/yard.txt";
    private static final String INVENTORY_FILE = "./data/inventory.txt";
    boolean keepGoing = true;
    String command = null;
    Scanner input;
    Yard yard = new Yard();
    Inventory inventory = new Inventory();
    GameItems gameItems = new GameItems();
    GameCats gameCats = new GameCats();
    JsonObject yardJson;
    JsonObject inventoryJson;


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

        //loadGame();


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
            saveGame();
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


    //EFFECTS: saves the state of the Cat Lawn Yard and Inventory to YARD_FILE and INVENTORY_FILE,
    //         respectively
    private void saveGame() {
        JsonObject jsonObjectYard = saveYard();
        JsonObject jsonObjectInventory = saveInventory();
        try {
            FileWriter fileWriter = new FileWriter(YARD_FILE);
            fileWriter.write(jsonObjectYard.toString());
            fileWriter.close();
            System.out.println("Successfully saved yard data!");
            FileWriter fileWriter1 = new FileWriter(INVENTORY_FILE);
            fileWriter1.write(jsonObjectInventory.toString());
            fileWriter1.close();
            System.out.println("Successfully saved inventory data!");
        } catch (FileNotFoundException e) {
            System.out.println("Problem saving game data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//        try {
//            Writer writer = new Writer(new File(YARD_FILE));
//            String jsonToString = yardJson.toString();
//            writer.write(jsonToString);
//            writer.close();
//            System.out.println("Yard data saved to" + YARD_FILE);
////            Writer writer1 = new Writer(new File(INVENTORY_FILE));
////            writer1.write(inventory);
////            writer1.close();
////            System.out.println("Inventory data saved to" + INVENTORY_FILE);
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Problem saving game data");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


    public JsonObject saveYard() {
        yardJson = new JsonObject();
        yardJson.add("cats", saveCats());
        yardJson.add("food", saveFood());
        yardJson.add("toys", saveToys());

        return yardJson;

    }

    public JsonObject saveInventory() {
        inventoryJson = new JsonObject();
        inventoryJson.addProperty("balance", inventory.getBalance());
        inventoryJson.add("inventoryList", saveInventoryList());

        return inventoryJson;
    }

    public JsonArray saveInventoryList() {
        JsonArray saveInventoryList = new JsonArray();
        for (InventoryEntry i : inventory.inventoryList) {
            Gson gson = new Gson();
            String saveInventoryEntry = gson.toJson(i);
            saveInventoryList.add(saveInventoryEntry);
        }
        return saveInventoryList;
    }


    //EFFECTS: saves the cats in the yard into an array of JSON objects
    public JsonArray saveCats() {
        JsonArray saveCats = new JsonArray();
        for (Cat cat : yard.cats) {
            Gson gson = new Gson();
            String saveCat = gson.toJson(cat);
            saveCats.add(saveCat);
        }

        return saveCats;
    }

    //EFFECTS: saves the food in the yard into an array of JSON objects
    public JsonArray saveFood() {
        JsonArray saveFoods = new JsonArray();
        for (Item food : yard.food) {
            Gson gson = new Gson();
            String saveFood = gson.toJson(food);
            saveFoods.add(saveFood);

        }

        return saveFoods;
    }

    //EFFECTS: saves the toys in the yard into an array of JSON objects
    public JsonArray saveToys() {
        JsonArray saveToys = new JsonArray();
        for (Item toy : yard.toys) {
            Gson gson = new Gson();
            String saveToy = gson.toJson(toy);
            saveToys.add(saveToy);

        }

        return saveToys;
    }


//        try {
//            Writer writer = new Writer(new File(YARD_FILE));
//            writer.write(yard);
//            writer.close();
//            System.out.println("Yard data saved to" + YARD_FILE);
//            Writer writer1 = new Writer(new File(INVENTORY_FILE));
//            writer1.write(inventory);
//            writer1.close();
//            System.out.println("Inventory data saved to" + INVENTORY_FILE);
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Problem saving game data");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//    }

//    // MODIFIES: this
//    // EFFECTS: loads game from YARD_FILE and INVENTORY_FILE, if that file exists;
//    //otherwise initializes CatLawn with default values
//    private void loadGame() {
//        try {
//            Yard yards = Reader.readYardData(new File(YARD_FILE), gameItems, gameCats);
//            List<Inventory> inventories = Reader.readInventoryData(new File(INVENTORY_FILE));
//
//
//        } catch (IOException e) {
//            init();
//        }
//    }
//
//    private void init() {
//        inventory = new Inventory();
//        yard = new Yard();
//    }


//    public void loadGame() {
//        loadYard();
//        //loadInventory();
//
//    }

//    public void loadYard() {
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(YARD_FILE)) {
//            YardJsonParser loadedYard = gson.fromJson(reader, YardJsonParser.class);
//            yard = loadedYard;
//        } catch (IOException e) {
//            emptyYard();
//        }
//    }

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

//    public void emptyYard() {
//        yard = new Yard();
//    }

}










