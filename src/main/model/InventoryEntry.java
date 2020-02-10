package model;

//class that represents an item as it exists within the inventory
public class InventoryEntry {
    Item item;
    int quantity;

    //Constructs an Inventory entry, consisting of a Item and a quantity
    public InventoryEntry(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;

    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    //REQUIRES: quantity is non-negative
    //MODIFIES: this
    //EFFECTS: adds to the quantity of the inventory entry
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
