package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


//used https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java to do random functionality
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

    //EFFECT: randomly generates a cat from a given list
    public Cat generateCommonCat() {
        int random = ThreadLocalRandom.current().nextInt(0, commonGameCats.size());
        return commonGameCats.get(random);
        
    }


}








