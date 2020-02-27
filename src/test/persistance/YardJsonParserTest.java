package persistance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CatLawnApp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileReader;
import java.util.ArrayList;

public class YardJsonParserTest {

    private FileReader reader;
    Yard testYard;
    ArrayList<Cat> testCatsList;
    ArrayList<Item> testFoodList;
    ArrayList<Item> testToysList;
    Cat testCat;
    Item testToy;
    Item testFood;
    YardJsonParser testYardJsonParser;
    String TEST_YARD_FILE = "./data/testYard.json";
    String FAKE_YARD_FILE = "./././././";
    CatLawnApp catAppTest;
    CatLawnApp catAppTest1;

    @BeforeEach
    void runBefore() {
        testYard = new Yard();
        testYardJsonParser = new YardJsonParser(catAppTest = new CatLawnApp(testYard));
        testCatsList = new ArrayList<>();
        testFoodList = new ArrayList<>();
        testToysList = new ArrayList<>();
        testCat = new Cat("name", "breed", "coat", "rarity",
                "foodpref", "toypref");
        testToy = new Toy("toy", 1);
        testFood = new Food("food", 2);
    }



    @Test

    void testParsedDataGoodFile() {
        assertTrue(catAppTest.loadGame(TEST_YARD_FILE));

    }

    @Test

    void testParsedDataBadFile() {
        assertFalse(catAppTest.loadGame(FAKE_YARD_FILE));
    }










}
