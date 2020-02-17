package model;

import org.junit.jupiter.api.BeforeEach;

public class GameItemsTest {
    GameItems testGameItems;
    Item testFood;
    Item testToy;


    @BeforeEach
    void runBefore() {
        testGameItems = new GameItems();
        testFood = new Food("food", 1);
        testToy = new Toy("toy", 2);
    }



}
