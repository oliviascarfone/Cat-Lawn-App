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
    CatLawnApp catAppTest;
    CatLawnApp catAppTest1;

    @BeforeEach
    void runBefore() {
        testYardJsonParser = new YardJsonParser(catAppTest = new CatLawnApp());
        testYardJsonParser = new YardJsonParser(catAppTest1 = new CatLawnApp());
        testYard = new Yard();
        testCatsList = new ArrayList<>();
        testFoodList = new ArrayList<>();
        testToysList = new ArrayList<>();
        testCat = new Cat("name", "breed", "coat", "rarity",
                "foodpref", "toypref");
        testToy = new Toy("toy", 1);
        testFood = new Food("food", 2);
    }









}
