package persistance;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterInventoryTest {
    JsonWriterInventory testJsonWriterInventory;
    Inventory testInventory;
    InventoryEntry testInventoryEntry;
    Item testToy;
    Item testFood;
    String TEST_INVENTORY_FILE = "./data/testInventory.json";
    InventoryEntry testInventoryEntry2;

    @BeforeEach
    void runBefore() {
        testJsonWriterInventory = new JsonWriterInventory();
        testInventory = new Inventory();
        testToy = new Toy("toy", 1);
        testFood = new Food("food", 2);
        testInventoryEntry = new InventoryEntry(testToy, 1);
        testInventoryEntry2 = new InventoryEntry(testFood, 1);
    }

    @Test
    void testConstructor() {
        assertEquals(testJsonWriterInventory.getClass(), JsonWriterInventory.class);
    }

    @Test
    void saveGameTestGoodDataGoodFile() {
        assertTrue(testJsonWriterInventory.saveGame(testInventory, TEST_INVENTORY_FILE));

    }

    @Test
    void saveGameTestGoodDataBadFile() {
        assertFalse(testJsonWriterInventory.saveGame(testInventory, "/./././."));
    }







}
