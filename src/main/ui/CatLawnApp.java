package ui;

import model.Inventory;
import model.Yard;

import java.util.Locale;
import java.util.Scanner;

//ideas taken from AccountNotRobust program
public class CatLawnApp {
    boolean keepGoing = true;
    String command = null;
    Scanner input;
    Yard yard = new Yard();
    Inventory inventory = new Inventory();

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
        System.out.println("\ti -> check my inventory");
        System.out.println("\ts -> shop for items");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            System.out.println(yard.catsInYard());
        } else if (command.equals("i")) {
            System.out.println(yard.itemsInYard());
        } else if (command.equals("s")) {
            buyItems();
        } else if (command.equals("q")) {
            System.out.println("goodbye!");
        } else {
            System.out.println("Invalid selection, please try another menu option");
        }
    }
}



