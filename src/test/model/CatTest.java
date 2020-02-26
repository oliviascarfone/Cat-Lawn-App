package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatTest {
    Cat testCat;

    @BeforeEach
    void runBefore() {
        testCat = new Cat("name", "breed", "coat", "rarity",
                "foodpref", "toypref");
    }

    @Test
    void testConstructor() {
        assertEquals( "name", testCat.getName());


    }

    @Test
    void testGetBreed() {
        assertEquals( "breed", testCat.getBreed());
    }

    @Test
    void testGetRarityLevel() {
        assertEquals("rarity", testCat.getRarityLevel());

    }

    @Test
    void testFoodPref() {
        assertEquals( "foodpref", testCat.getFoodPreference());
    }

    @Test
    void testCoat() {
        assertEquals("coat", testCat.getCoat());
    }

    @Test
    void testToyPref() {
        assertEquals("toypref", testCat.getToyPreference());
    }



}
