package ui;

import model.*;

import java.util.Locale;
import java.util.Scanner;

//ideas taken from AccountNotRobust program
public class CatLawnApp {
    boolean keepGoing = true;
    String command = null;
    Scanner input;
    Yard yard = new Yard();
    Inventory inventory = new Inventory();
    Item kibble = new Food("kibble", 0);
    Item string = new Toy("string", 0);

    public CatLawnApp() {
        runCatLawnApp();
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
            System.out.println(yard.catsInYard());
        } else if (command.equals("y")) {
            showItemsInYard();
        } else if (command.equals("i")) {
            System.out.println(inventory.checkInventoryItems());
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

    private void placeItemsInYard() {
        String selectedItemName;
        Item selectedItem;
        if (inventory.inventory.size() == 0) {
            System.out.println("You have nothing to place! Try buying something first");
            return;
        } else {
            System.out.println("type the name of the item you want to place");
            Scanner newscan = new Scanner(System.in);
            String option = newscan.nextLine();
            for (int i = 0; inventory.inventory.size() > i; i++) {
                selectedItem = inventory.inventory.get(i).getItem();
                selectedItemName = inventory.inventory.get(i).getItem().getName();
                if (selectedItemName.equals(option)) {
                    int amount = inventory.inventory.get(i).getQuantity();
                    inventory.inventory.get(i).setQuantity(amount - 1);
                    yard.addItemToYard(selectedItem);
                    System.out.println("placed " + selectedItemName);
                    return;
                } else {
                    System.out.println("item not found, check your inventory and try again");
                }
            }







        }


    }


    public void shopItems() {
        String choice = "";



        while (! (choice.equals("bf")) || (choice.equals("bt"))) {
            System.out.println("\tbf-> buy food");
            System.out.println("\tbt-> buy toys");
            choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("bf")) {
                System.out.println("bought food!");
                inventory.buyItem(kibble, 1);
                return;
            }
            if (choice.equals("bt")) {
                System.out.println("bought toy!");
                inventory.buyItem(string, 1);
                return;
            }

        }
    }
        //use if statements
        //System.out.println("\tf-> buy food");
        //System.out.println("\tt-> buy toys");
        //shop toys --> list of toys


    public void showItemsInYard() {
        String selection = "";

        while (!(selection.equals("f") || selection.equals("t"))) {
            System.out.println("\tf-> show food");
            System.out.println("\tt-> show toys");
            selection = input.next();
            selection = selection.toLowerCase();

            if (command.equals("f")) {
                System.out.println(yard.itemsInYard(yard.food));
            } else {
                System.out.println(yard.itemsInYard(yard.toys));
            }
            return;
        }
    }


}








