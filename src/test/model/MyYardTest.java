package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyYardTest {
    Yard testYard;
    ArrayList<Cat> testCatsList;
    ArrayList<Item> testFoodList;
    ArrayList<Item> testToysList;
    Cat testCat;
    Item testToy;
    Item testFood;

    @BeforeEach
    void runBefore() {
        testYard = new Yard();
        testCatsList = new ArrayList<>();
        testFoodList = new ArrayList<>();
        testToysList = new ArrayList<>();
        testCat = new Cat("name", "breed", "coat", "rarity",
                "foodpref", "toypref");
        testToy = new Toy("toy", 1);
        testFood = new Food("food", 2);
    }

    @Test
    void testConstructor(){
        assertEquals(testYard.cats.size(), 0);
        assertEquals(testYard.items.size(), 0);


    }

    @Test
    void testAddCatsToYard() {

    }


    @Test
    void testAddItemsToYard(){
        testYard.addItemToYard(testFood);
        assertEquals(testYard.items.size(), 1);


    }

    @Test
    void testCatsInYard(){
        assertEquals(testYard.catsInYard(), "There are no cats in your yard!");
        testYard.cats.add(testCat);
        assertEquals(testYard.catsInYard(), "name is visiting your lawn!\n");


    }






}