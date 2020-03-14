package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;




//used https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java and
// https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
// to do random functionality

//represents all available cats in the game
public class GameCats {
    ArrayList<Cat> commonGameCats;
    ArrayList<Cat> uncommonGameCats;
    Random random;
    ArrayList<String> catNames;

    public GameCats() {
        commonGameCats = new ArrayList<>();
        uncommonGameCats = new ArrayList<>();
        random = new Random();
        catNames = catNamesAsStrings();

    }

    //MODIFIES: this
    //EFFECTS: Creates list of Common Cats in the game

    public void listCommonCats() {
        Cat moki = new Cat("Moki", "Ragdoll", "Point",
                "Common", "Kibble", "Spring");
        Cat sushi = new Cat("Sushi", "Siamese", "Point",
                "Common", "Kibble", "Ribbon");
        Cat evie = new Cat("Evie", "Longhair", "White", "Common",
                "Kibble", "String");
        Cat luna = new Cat("Luna", "Shorthair", "Point", "Common",
                "Kibble", "Brush");
        Cat jiji = new Cat("Jiji", "Shorthair", "Black", "Common",
                "Kibble", "Spring");
        Cat nala = new Cat("Nala", "Shorthair", "Black", "Common",
                "Kibble", "Mouse");
        Cat milo = new Cat("Milo", "Munchkin", "Grey", "Common",
                "Tuna", "Spring");
        commonGameCats.add(moki);
        commonGameCats.add(sushi);
        commonGameCats.add(evie);
        commonGameCats.add(luna);
        commonGameCats.add(jiji);
        commonGameCats.add(nala);
        commonGameCats.add(milo);

    }

    //MODIFIES: this
    //EFFECTS: creates list of Uncommon Cats found in the game

    public void listUncommonCats() {
        Cat mimi = new Cat("Mimi", "Shorthair", "Black", "Uncommon",
                "Tuna", "Mouse");
        Cat roddick = new Cat("Roddick","Longhair", "Ginger", "Uncommon",
                        "Salmon", "Brush");
        Cat sesame = new Cat("Sesame","Longhair", "Grey", "Uncommon",
                "Kibble", "Mouse");
        Cat kiwi = new Cat("Kiwi","Shorthair", "Black", "Uncommon",
                "Kibble", "Spring");
        uncommonGameCats.add(mimi);
        uncommonGameCats.add(roddick);
        uncommonGameCats.add(sesame);
        uncommonGameCats.add(kiwi);

    }

    //REQUIRES: list of common cats is not empty
    //EFFECT: randomly generates a common cat
    public Cat generateCommonCat() {
        int r = random.nextInt(commonGameCats.size());
        return commonGameCats.get(r);
    }

    //REQUIRES: list of uncommon cats is not empty
    //EFFECT: randomly generates an uncommon cat
    public Cat generateUncommonCat() {
        int x = random.nextInt(uncommonGameCats.size());
        return uncommonGameCats.get(x);
    }

    //EFFECTS: returns the cats names in a list
    public ArrayList<String> catNamesAsStrings() {
        for (int i = 0; i < commonGameCats.size(); i++) {
            catNames.add(commonGameCats.get(i).getName());

        }
        for (int i = 0; i < uncommonGameCats.size(); i++) {
            catNames.add(uncommonGameCats.get(i).getName());
        }
        return catNames;
    }

    
}








