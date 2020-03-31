package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    Inventory testInventory;
    Item testItemFood;
    Item testItemToy;
    InventoryEntry testInventoryEntryFood;
    InventoryEntry testInventoryEntryToy;


    @BeforeEach
    void runBefore(){
        testItemFood = new Food("Kibble", 1);
        testItemToy = new Toy("Spring", 1);
        testInventory = new Inventory();
        testInventoryEntryFood = new InventoryEntry(testItemFood, 2);
        testInventoryEntryToy = new InventoryEntry(testItemToy, 1);


    }

    @Test
    void testConstructor(){
        assertEquals(testInventory.inventoryList, new ArrayList<>());
        assertEquals(testInventory.inventoryList.size(), 0);
    }

    @Test
    void buyOneItemNoMoneyTest(){
       // assertFalse(testInventory.buyItem(testItemFood, 2));
        assertEquals(testInventory.inventoryList.size(), 0);

    }
//    @Test
//    void confirmPurchaseTest(){
//        testInventory.setBalance(10);
//        assertTrue(testInventory.buyItem("Kibble", 2));
//
//    }

    @Test
    void buyMultipleItems(){
        testInventory.setBalance(10);
        testInventory.buyItem("Kibble");
        testInventory.buyItem("Kibble");
        assertEquals(testInventory.inventoryList.size(), 2);

    }

    @Test
    void buyOneOfEachItem() {
        testInventory.buyItem("Spring");
        testInventory.buyItem("Kibble");
        assertEquals(testInventory.inventoryList.size(), 2);

    }

    @Test
    void testBadSelection() {
        testInventory.buyItem("test");
        assertEquals(testInventory.inventoryList.size(), 0);
    }

//    @Test
//    void buySameItems(){
//        testInventory.setBalance(100);
//        testInventory.buyItem(testItemToy, 1);
//        testInventory.buyItem(testItemFood, 2);
//        testInventory.buyItem(testItemToy, 2);
//        assertEquals(testInventory.inventoryList.size(), 2);
//    }

//    @Test
//    void checkBalanceTest(){
//        testInventory.setBalance(20);
//        assertEquals(testInventory.getBalance(), 20);
//        testInventory.buyItem(testItemFood, 2);
//        assertEquals(testInventory.getBalance(), 18);
//        testInventory.buyItem(testItemToy, 1);
//        assertEquals(testInventory.getBalance(), 17);
//
//    }

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
        testInventory.inventoryList.add(testInventoryEntryFood);
        testInventory.removeItemFromInventory("Kibble", testInventory);
        assertEquals(testInventory.inventoryList.size(), 0);

    }
    @Test
    void removeMultipleItemsFromInventoryTest() {
        testInventory.inventoryList.add(testInventoryEntryFood);
        testInventory.inventoryList.add(testInventoryEntryToy);
        testInventory.removeItemFromInventory("Kibble", testInventory);
        assertEquals(testInventory.inventoryList.size(), 1);
    }

    @Test
    void removeMultipleSameItemsFromInventoryTest() {
        testInventory.inventoryList.add(testInventoryEntryFood);
        testInventory.inventoryList.add(testInventoryEntryToy);
        testInventory.inventoryList.add(testInventoryEntryToy);
        testInventory.removeItemFromInventory("Spring", testInventory);
        assertEquals(testInventory.inventoryList.size(), 2);
    }



    @Test
    void clearInventoryTest() {
        testInventory.buyItem("Kibble");
        testInventory.buyItem("Spring");
        testInventory.setBalance(2);
        assertEquals(testInventory.inventoryList.size(), 2);
        assertEquals(testInventory.getBalance(), 2);
        testInventory.clearInventory(testInventory);
        assertEquals(testInventory.inventoryList.size(), 0);
        assertEquals(testInventory.getBalance(), 0);
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
        testInventory.inventoryList.add(testInventoryEntryToy);
        testInventory.inventoryList.add(testInventoryEntryFood);
        assertEquals("Spring:1,Kibble:2", testInventory.inventoryEntryQuantityNames());
    }






}
