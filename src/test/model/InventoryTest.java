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


    @BeforeEach
    void runBefore(){
        testItemFood = new Food("food", 1);
        testItemToy = new Toy("toy", 1);
        testInventory = new Inventory();
        testInventoryEntry = new InventoryEntry(testItemFood, 2);


    }

    @Test
    void testConstructor(){
        assertEquals(testInventory.inventory, new ArrayList<>());
        assertEquals(testInventory.inventory.size(), 0);
    }

    @Test
    void buyOneItemNoMoneyTest(){
        assertFalse(testInventory.buyItem(testItemFood, 2));
        assertEquals(testInventory.inventory.size(), 0);

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
        assertEquals(testInventory.inventory.size(), 2);

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






}
