package persistance;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.jar.JarEntry;

public class JsonWriterTest {
    JsonWriter testJsonWriter;
    Yard testYard;
    ArrayList<Cat> testCatsList;
    ArrayList<Item> testFoodList;
    ArrayList<Item> testToysList;
    Cat testCat;
    Item testToy;
    Item testFood;
    String YARD_FILE = "./data/yard.json";
    String FAKE_FILE = "./data/fake.json";


    @BeforeEach
    void runBefore() {
        testJsonWriter = new JsonWriter();
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
        assertEquals(testJsonWriter.getClass(), JsonWriter.class);
    }

    @Test
    void saveGameTest() {

    }

}




