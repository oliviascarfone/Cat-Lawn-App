package ui;

import model.*;

import java.util.Scanner;

//ideas taken from AccountNotRobust program
public class CatLawnApp {
    boolean keepGoing = true;
    String command = null;
    Scanner input;
    Yard yard = new Yard();
    Inventory inventory = new Inventory();
    GameItems gameItems = new GameItems();


    public CatLawnApp() {
        runCatLawnApp();
        gameItems.listToys();
        gameItems.listFood();
    }

    //EFFECTS: processes user input
    private void runCatLawnApp() {
        input = new Scanner(System.in);
        command = input.nextLine();



        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
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
        System.out.println("\tc -> see the cats in my yard");
        System.out.println("\ty -> see the items in my yard");
        System.out.println("\ti -> check my inventory");
        System.out.println("\tp -> place items in yard");
        System.out.println("\ts -> shop for items");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            yard.addCatToYard();
            System.out.println(yard.catsInYard());
        } else if (command.equals("y")) {
            showItemsInYard();
        } else if (command.equals("i")) {
            System.out.println(inventory.checkInventoryItems());
            System.out.println("You have $" + inventory.getBalance());
        } else if (command.equals("p")) {
            placeItemsInYard();
        } else if (command.equals("s")) {
            shopItems();
        } else if (command.equals("q")) {
            System.out.println("goodbye!");
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
        while (! (choice.equals("bf")) || (choice.equals("bt"))) {
            System.out.println("\tbf-> buy food");
            System.out.println("\tbt-> buy toys");
            choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("bf")) {
                buyFood();
                return;
            }
            if (choice.equals("bt")) {
                buyToys();
                return;


            }

        }
    }

    public void buyFood() {
        Food selectedFoodToPurchase;
        String selectedFoodToPurchaseName;
        String choice;
        //gameItems.listFood();
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
       // gameItems.listToys();
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


}








