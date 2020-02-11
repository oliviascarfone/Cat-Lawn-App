package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameCatTest {
    ArrayList<Cat> testGameCats;
    Cat testCat;

    @BeforeEach
    void runBefore() {
        testGameCats = new ArrayList<Cat>();
        testCat = new Cat("name", "breed", "coat", "rare",
                "food", "toy");

    }


    @Test
    void testConstructor() {
        assertEquals(testGameCats, new ArrayList<Cat>());
    }









}
