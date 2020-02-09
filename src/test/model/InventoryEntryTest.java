package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryEntryTest {

    InventoryEntry testInventoryEntry1;
    InventoryEntry testInventoryEntry2;
    Item testItemFood;
    Item testItemToy;

    @BeforeEach
    void runBefore() {
        testItemFood = new Food("food", 0);
        testItemToy = new Toy("toy", 0);

        testInventoryEntry1 = new InventoryEntry(testItemFood, 1);
        testInventoryEntry2 = new InventoryEntry(testItemToy, 2);


    }

    @Test
    void testConstructor() {
        assertEquals(testInventoryEntry1.getItem().getName(), "food");
    }

}

