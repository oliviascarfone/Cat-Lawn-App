package model;

import java.util.ArrayList;
import java.util.LinkedList;

//represents an inventory for storing money and purchased items
//used information from A2-GroceryBill lab
public class Inventory {
    private int balance;
    public ArrayList<InventoryEntry> inventory;
    private static final int MAX_INVENTORY_SIZE = 5;

    //EFFECTS: creates an empty inventory with no items and no money
    public Inventory() {
        inventory = new ArrayList<>();
        balance = 0;

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    // MODIFIES: this
    // EFFECTS: returns true and adds item of given quantity to the inventory if there are sufficient funds
    // If item is already in the inventory increase the quantity of existing entry.
    // Item is purchased only if sufficient funds, produces false if not enough funds

    public boolean buyItem(Item item, int quantity) {
        int indicator = 0;
        if ((item.getCost() * quantity) <= balance) {
            balance = (balance - (item.getCost() * quantity));

            for (int i = 0; i < inventory.size(); i++) {
                String eachItemName = inventory.get(i).getItem().getName();
                if (eachItemName == item.getName()) {
                    indicator += 1;
                    inventory.get(i).addQuantity(quantity);

                }
            }
            if (indicator == 0) {
                InventoryEntry boughtItem = new InventoryEntry(item, quantity);
                inventory.add(boughtItem);

            }
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: shows all items in your inventory
    public String checkInventoryItems() {
        String inventoryToString = "";
        if (inventory.size() > 0) {
            for (int i = 0; i < inventory.size(); i++) {
                String eachItem = inventory.get(i).getItem().getName();

                inventoryToString += (String.format("%s is in your inventory!\n",
                        eachItem));

            }
            return inventoryToString;
        } else {
            inventoryToString += "There is nothing in your inventory!";
            return inventoryToString;
        }
    }

    public void addInventoryItemToYard(Item item) {


    }



}























