package persistance;

import com.google.gson.JsonArray;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterYardTest {
    JsonWriterYard testJsonWriterYard;
    Yard testYard;
    ArrayList<Cat> testCatsList;
    ArrayList<Item> testFoodList;
    ArrayList<Item> testToysList;
    Cat testCat;
    Item testToy;
    Item testFood;
    String TEST_YARD_FILE = "./data/testYard.json";


    @BeforeEach
    void runBefore() {
        testJsonWriterYard = new JsonWriterYard();
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
    void testConstructor() {
        assertEquals(testJsonWriterYard.getClass(), JsonWriterYard.class);
    }

    @Test
    void saveGameTestGoodDataGoodFile() {
       assertTrue(testJsonWriterYard.saveGame(testYard, TEST_YARD_FILE));

    }

    @Test
    void saveGameTestGoodDataBadFile() {
        assertFalse(testJsonWriterYard.saveGame(testYard, "/./././."));
    }

    @Test
    void testSaveCats() {
        testYard.cats.add(testCat);
        JsonArray jsonArray = JsonWriterYard.saveCats(testYard);
        assertTrue(jsonArray.isJsonArray());
    }

    @Test
    void testSaveFood() {
        testYard.food.add(testFood);
        JsonArray jsonArray = JsonWriterYard.saveFood(testYard);
        assertTrue(jsonArray.isJsonArray());
    }

    @Test
    void testSaveToys() {
        testYard.toys.add(testToy);
        JsonArray jsonArray = JsonWriterYard.saveToys(testYard);
        assertTrue(jsonArray.isJsonArray());

    }

}
