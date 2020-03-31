//package persistance;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import model.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
////import ui.CatLawnApp;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import java.util.ArrayList;
//import java.util.jar.JarEntry;
//
//public class JsonWriterTest {
//    JsonWriter testJsonWriter;
//    Yard testYard;
//    Inventory testInventory;
//    ArrayList<Cat> testCatsList;
//    ArrayList<Item> testFoodList;
//    ArrayList<Item> testToysList;
//    Cat testCat;
//    Item testToy;
//    Item testFood;
//    String TEST_YARD_FILE = "./data/testYard.json";
//    String TEST_INVENTORY_FILE = "./data/testInventory.json";
//    //CatLawnApp catApp = new CatLawnApp();
//
//
//
//    @BeforeEach
//    void runBefore() {
//        testJsonWriter = new JsonWriter(testYard, testInventory, TEST_YARD_FILE, TEST_INVENTORY_FILE);
//        testYard = new Yard();
//        testInventory = new Inventory();
//        testCatsList = new ArrayList<>();
//        testFoodList = new ArrayList<>();
//        testToysList = new ArrayList<>();
//        testCat = new Cat("name", "breed", "coat", "rarity",
//                "foodpref", "toypref");
//        testToy = new Toy("toy", 1);
//        testFood = new Food("food", 2);
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals(testJsonWriter.getClass(), JsonWriter.class);
//    }
//
//    @Test
//    void saveGameTestGoodDataGoodFile() {
//       assertTrue(testJsonWriter.saveGame(testYard, testInventory, TEST_YARD_FILE,TEST_INVENTORY_FILE));
//
//    }
//
//    @Test
//    void saveGameTestGoodDataBadFile() {
//        assertFalse(testJsonWriter.saveGame(testYard, testInventory,"/./././.", "/././."));
//    }
//
//    @Test
//    void testSaveCats() {
//        testYard.cats.add(testCat);
//        JsonArray jsonArray = JsonWriter.saveCats(testYard);
//        assertTrue(jsonArray.isJsonArray());
//    }
//
//    @Test
//    void testSaveFood() {
//        testYard.food.add(testFood);
//        JsonArray jsonArray = JsonWriter.saveFood(testYard);
//        assertTrue(jsonArray.isJsonArray());
//    }
//
//    @Test
//    void testSaveToys() {
//        testYard.toys.add(testToy);
//        JsonArray jsonArray = JsonWriter.saveToys(testYard);
//        assertTrue(jsonArray.isJsonArray());
//    }
//
//
//
//
//
//
//}
//
//
//
//
