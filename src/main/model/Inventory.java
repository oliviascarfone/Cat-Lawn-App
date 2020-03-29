package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

//represents an inventory for storing money and purchased items
//used information from A2-GroceryBill lab
public class Inventory {
    public int balance;
    public ArrayList<InventoryEntry> inventoryList;
    private static final int MAX_INVENTORY_SIZE = 5;

    //EFFECTS: creates an empty inventory with no items and no money
    public Inventory() {
        inventoryList = new ArrayList<>();
        balance = 0;

    }


    //
//    //EFFECTS: creates an Inventory from saved data
//    public Inventory(ArrayList<InventoryEntry> inventoryList, int balance) {
//        this.inventoryList = inventoryList;
//        this.balance = balance;
//    }
//
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

            //Functionality for having money - a feature that is not currently being used
//            for (int i = 0; i < inventoryList.size(); i++) {
//                String eachItemName = inventoryList.get(i).getItem().getName();
//                if (eachItemName == item.getName()) {
//                    indicator += 1;
//                    inventoryList.get(i).addQuantity(quantity);
//
//                }
//            }
            if (indicator == 0) {
                InventoryEntry boughtItem = new InventoryEntry(item, quantity);
                inventoryList.add(boughtItem);

            }
            return true;
        } else {
            return false;
        }
    }

//    //EFFECTS: shows all items in your inventory
//    public String checkInventoryItems() {
//        String inventoryToString = "";
//        if (inventoryList.size() > 0) {
//            for (int i = 0; i < inventoryList.size(); i++) {
//                String eachItem = inventoryList.get(i).getItem().getName();
//                int eachItemQuantity = inventoryList.get(i).getQuantity();
//                inventoryToString += (String.format("%s (x%s) is in your inventory!\n",
//                        eachItem, eachItemQuantity));
//            }
//            return inventoryToString;
//        } else {
//            inventoryToString += "There is nothing in your inventory!";
//            return inventoryToString;
//        }
//    }



//Used for Console
//    //REQUIRES: non-empty Inventory and Inventory contains the parameter item
//    //MODIFIES: Inventory
//    //EFFECTS: Removes a quantity from the inventory, removes entry if quantity is zero.
//    public void removeItemFromInventory(Item item) {
//        int amount;
//
//        for (int i = 0; inventoryList.size() > i; i++) {
//            if (inventoryList.get(i).getItem().equals(item)) {
//                amount = inventoryList.get(i).getQuantity();
//                inventoryList.get(i).setQuantity(--amount);
//                if (inventoryList.get(i).getQuantity() == 0) {
//                    inventoryList.remove(i);
//                }
//
//            }
//        }
//
//    }

    //EFFECTS: returns inventory entry names and quantity in the inventory
    public String inventoryEntryQuantityNames() {
        String inventoryEntryQuantityNames = "";
        for (int i = 0; i < inventoryList.size(); i++) {
            String inventoryEntryName = inventoryList.get(i).getItem().getName();
            int inventoryEntryQuantity = inventoryList.get(i).getQuantity();
            if (i != (inventoryList.size() - 1)) {
                inventoryEntryQuantityNames += String.format("%s:%s" + ",",
                        inventoryEntryName, inventoryEntryQuantity);

            } else {
                inventoryEntryQuantityNames += String.format("%s:%s",
                        inventoryEntryName, inventoryEntryQuantity);
            }
        }
        return inventoryEntryQuantityNames;
    }

//    public Inventory clearInventory(Inventory inventoryObj) {
//        inventoryObj.inventoryList.clear();
//        inventoryObj.setBalance(0);
//        return inventoryObj;
//    }

    //EFFECTS: removes item from inventory has the same name as the given string
    public void removeItemFromInventory(String selection, Inventory gameInventory) {
        for (int i = 0; i < gameInventory.inventoryList.size(); i++) {
        //for (InventoryEntry e : gameInventory.inventoryList) {
            String itemName = gameInventory.inventoryList.get(i).item.getName();
//            String itemName = getItem(i).getName();
            if (itemName == selection) {
                gameInventory.inventoryList.remove(i);
            }
        }
    }

    public void clearInventory(Inventory inv) {
        inv.inventoryList.clear();
        inv.setBalance(0);
    }





}









































