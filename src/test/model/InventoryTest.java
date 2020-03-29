package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    Inventory testInventory;
    Item testItemFood;
    Item testItemToy;
    InventoryEntry testInventoryEntry;
    InventoryEntry testInventoryEntry1;


    @BeforeEach
    void runBefore(){
        testItemFood = new Food("food", 1);
        testItemToy = new Toy("toy", 1);
        testInventory = new Inventory();
        testInventoryEntry = new InventoryEntry(testItemFood, 2);
        testInventoryEntry1= new InventoryEntry(testItemToy, 1);


    }

    @Test
    void testConstructor(){
        assertEquals(testInventory.inventoryList, new ArrayList<>());
        assertEquals(testInventory.inventoryList.size(), 0);
    }

    @Test
    void buyOneItemNoMoneyTest(){
        assertFalse(testInventory.buyItem(testItemFood, 2));
        assertEquals(testInventory.inventoryList.size(), 0);

    }
    @Test
    void confirmPurchaseTest(){
        testInventory.setBalance(10);
        assertTrue(testInventory.buyItem(testItemFood, 2));

    }

    @Test
    void buyMultipleItems(){
        testInventory.setBalance(10);
        assertTrue(testInventory.buyItem(testItemFood, 1));
        assertTrue(testInventory.buyItem(testItemToy, 4));
        assertEquals(testInventory.inventoryList.size(), 2);

    }

    @Test
    void buySameItems(){
        testInventory.setBalance(100);
        testInventory.buyItem(testItemToy, 1);
        testInventory.buyItem(testItemFood, 2);
        testInventory.buyItem(testItemToy, 2);
        assertEquals(testInventory.inventoryList.size(), 2);
    }

    @Test
    void checkBalanceTest(){
        testInventory.setBalance(20);
        assertEquals(testInventory.getBalance(), 20);
        testInventory.buyItem(testItemFood, 2);
        assertEquals(testInventory.getBalance(), 18);
        testInventory.buyItem(testItemToy, 1);
        assertEquals(testInventory.getBalance(), 17);

    }

//    @Test
//    void checkInventoryItemsTest(){
//        testInventory.inventoryList.add(testInventoryEntry);
//        testInventory.inventoryList.add(new InventoryEntry(testItemToy, 3));
//        assertEquals(testInventory.checkInventoryItems(), "food (x2) is in your inventory!\n" +
//                "toy (x3) is in your inventory!\n");
//
//    }

//    @Test
//    void checkInventoryItemsWithNoItems() {
//        assertEquals(testInventory.checkInventoryItems(), "There is nothing in your inventory!");
//    }

    @Test
    void removeItemFromInventoryTest() {
        testInventory.inventoryList.add(testInventoryEntry);
        testInventory.removeItemFromInventory("Kibble", testInventory);
        assertEquals(testInventory.inventoryList.size(), 1);
//        testInventory.removeItemFromInventory(testItemFood);
//        assertEquals(testInventory.inventoryList.size(), 0);

    }
//
//    @Test
//    void removeItemFromInventoryWithMultipleItemTypesTest() {
//        testInventory.inventoryList.add(testInventoryEntry);
//        testInventory.inventoryList.add(testInventoryEntry1);
//        testInventory.removeItemFromInventory(testItemToy);
//        assertEquals(testInventory.inventoryList.size(), 1);
//
//    }

    @Test
    void testInventoryAndQuantity() {
        testInventory.inventoryList.add(testInventoryEntry1);
        testInventory.inventoryList.add(testInventoryEntry);
        assertEquals("toy:1,food:2", testInventory.inventoryEntryQuantityNames());
    }






}
