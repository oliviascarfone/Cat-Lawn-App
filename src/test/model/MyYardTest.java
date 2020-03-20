package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyYardTest {
    Yard testYard;
    ArrayList<Cat> testCatsList;
    ArrayList<Item> testFoodList;
    ArrayList<Item> testToysList;
    Cat testCat;
    Cat testCat1;
    Cat testCat2;
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
        testCat1 = new Cat("Moki", "Ragdoll", "Point",
                "Common", "Kibble", "Spring");
        testCat2 = new Cat("Kiwi","Shorthair", "Black", "Uncommon",
                "Kibble", "Spring");

    }

    @Test
    void testConstructor(){
        assertEquals(testYard.cats.size(), 0);
        assertEquals(testYard.food.size(), 0);
        assertEquals(testYard.toys.size(), 0);


    }

    @Test
    void testAddCatsToYard() {

    }


    @Test
    void testAddItemsToYard(){
        testYard.addItemToYard(testFood);
        assertEquals(testYard.food.size(), 1);
        testYard.addItemToYard(testToy);
        assertEquals(testYard.toys.size(), 1);
        assertEquals(testYard.toys.get(0), testToy);


    }

    @Test
    void testCatsInYard(){
        assertEquals(testYard.catsInYard(), "There are no cats in your yard!");
        testYard.cats.add(testCat);
        assertEquals(testYard.catsInYard(), "name is visiting your lawn!\n");


    }
    @Test
    void testNoItemsInYard(){
        assertEquals(testYard.itemsInYard(testFoodList), "There are none placed in your yard!");
        assertEquals(testYard.itemsInYard(testToysList), "There are none placed in your yard!");

    }

    @Test
    void testOneItemInYard(){
        testFoodList.add(testFood);
        assertEquals(testYard.itemsInYard(testFoodList), "You have placed food in your yard!\n");

    }

    @Test
    void testMultipleItemsInYard() {
        testFoodList.add(testFood);
        testToysList.add(testToy);
        assertEquals(testYard.itemsInYard(testToysList),"You have placed toy in your yard!\n");
        assertEquals(testYard.itemsInYard(testFoodList),"You have placed food in your yard!\n");
    }

    @Test
    void addCatToLawnNoItemsTest(){
        testYard.addCatToYard();
        assertEquals(testYard.cats.size(), 0);
    }
    @Test
    void addCatsToLawnOneToyTest(){
        testYard.toys.add(testToy);
        testYard.addCatToYard();
        assertEquals(testYard.cats.size(), 1);
    }

    @Test
    void addCatsToLawnOneFoodTest() {
        testYard.food.add(testFood);
        testYard.addCatToYard();
        assertEquals(testYard.cats.size(), 1);

    }

    @Test
    void addCatsToLawnFoodAndToyTest() {
        testYard.food.add(testFood);
        testYard.toys.add(testToy);
        testYard.addCatToYard();
        assertEquals(testYard.cats.size(), 2);

    }

//    @Test
//    void catsAsArrayStringTest() {
//        testYard.cats.add(testCat);
//        assertEquals(testYard.catsInYardArray().get(0), "name is visiting your lawn!");
//    }


}