package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameCatTest {
    GameCats testGameCatsArray;
    Cat testCat;


    @BeforeEach
    void runBefore() {
        testGameCatsArray = new GameCats();
        testCat = new Cat("name", "breed", "coat", "rare",
                "food", "toy");

    }


    @Test
    void testConstructor() {
        assertEquals(testGameCatsArray.commonGameCats, new ArrayList<Cat>());
    }

    @Test
    void testHashmap() {
        assertEquals(testGameCatsArray.catPics.get("Moki"), "data/cats/moki.png");



    }









}
