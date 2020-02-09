package model;

import org.junit.jupiter.api.BeforeEach;

public class InventoryTest {

    Inventory testInventory;
    Item testItemFood;
    Item testItemToy;


    @BeforeEach
    void runBefore(){
        testInventory = new Inventory();
        testItemFood = new Food("food", 0);
        testItemToy = new Toy("toy", 0);

    }





}
