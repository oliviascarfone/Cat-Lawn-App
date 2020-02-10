package model;

import java.util.ArrayList;

public class GameCats {
    ArrayList<Cat> commonGameCats;
    ArrayList<Cat> uncommonGameCats;

    public GameCats() {
        commonGameCats = new ArrayList<>();
        uncommonGameCats = new ArrayList<>();

    }

    public void commonCats() {
        Cat moki = new Cat("Moki", "Ragdoll", "Point",
                "Common", "Kibble", "Spring");
        Cat sushi = new Cat("Sushi", "Siamese", "Point",
                "Common", "Kibble", "Ribbon");
        commonGameCats.add(moki);
        commonGameCats.add(sushi);
    }

    public void uncommonCats() {
        Cat mimi = new Cat("Mimi", "Shorthair", "Black", "Uncommon",
                "Tuna", "Mouse");
        Cat roddick = new Cat("Roddick","Longhair", "Ginger", "Uncommon",
                        "salmon", "Brush");
        Cat sesame = new Cat("Sesame","Longhair", "Grey", "Uncommon",
                "Kibble", "Mouse");
        uncommonGameCats.add(mimi);
        uncommonGameCats.add(roddick);
        uncommonGameCats.add(sesame);


    }
}







