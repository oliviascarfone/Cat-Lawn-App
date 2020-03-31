package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameCatTest {
    GameCats testGameCats;
    Cat testCat;


    @BeforeEach
    void runBefore() {
        testGameCats = new GameCats();
        testCat = new Cat("name", "breed", "coat", "rare",
                "food", "toy");

    }


    @Test
    void testConstructor() {
        assertEquals(testGameCats.commonGameCats, new ArrayList<Cat>());
    }

    @Test
    void testHashmap() {
        assertEquals(testGameCats.catPics.get("Moki"),
                testGameCats.retrieveCatPath("Moki"));
    }













}
