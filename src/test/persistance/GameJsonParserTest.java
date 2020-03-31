package persistance;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import ui.CatLawnApp;
import ui.Gui;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;

import java.util.ArrayList;

public class GameJsonParserTest {

    private FileReader reader;
    Yard testYard;
    ArrayList<Cat> testCatsList;
    ArrayList<Item> testFoodList;
    ArrayList<Item> testToysList;
    Cat testCat;
    Item testToy;
    Item testFood;
    GameJsonParser testGameJsonParser;
    String TEST_YARD_FILE = "./data/testYard.json";
    String TEST_INVENTORY_FILE = "./data/testInventory.json";
    String FAKE_FILE = "./././././";
    Gui gui;
    //CatLawnApp catAppTest1;

    @BeforeEach
    void runBefore() {
        testYard = new Yard();
        testGameJsonParser = new GameJsonParser(gui = new Gui());
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
        assertTrue(gui.loadGame(TEST_YARD_FILE, TEST_INVENTORY_FILE));

    }

    @Test

    void testParsedDataBadFile() {
        assertFalse(gui.loadGame(FAKE_FILE, FAKE_FILE));
    }










}
