package persistance;

import model.Item;

import java.util.ArrayList;

public class YardJsonParser {
    ArrayList<Cat> cats;
    ArrayList<Item> food;
    ArrayList<Item> toys;


    public class Cat {
        String name;
        String breed;
        String coat;
        String rarityLevel;
        String foodPreference;
        String toyPreference;
    }

    public class Item {
        String name;
        int cost;
    }

    


}
