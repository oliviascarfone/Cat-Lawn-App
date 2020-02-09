package model;

//Represents a cat having a name, breed, a coat colour, a rarity level, food preference, and item preference
//      rarity level corresponds to spawn rates (Common, Uncommon, Rare)
public class Cat {
    private String name;
    private String breed;
    private String coat;
    private String rarityLevel;
    private String foodPreference;
    private String toyPreference;

    private final Cat moki = new Cat("Moki", "Ragdoll", "Point",
            "Common", "Kibble", "Spring");
    private final Cat sushi = new Cat("Sushi", "Siamese", "Point",
            "Common", "Kibble", "Ribbon");
    private final Cat mimi = new Cat("Mimi", "Shorthair", "Black", "Uncommon",
            "Tuna", "Mouse");
    private final Cat roddick = new Cat("Roddick","Longhair", "Ginger", "Uncommon",
            "salmon", "Brush");

    public Cat(String name, String breed, String coat, String rarityLevel,
               String foodPreference, String toyPreference) {
        this.name = name;
        this.breed = breed;
        this.coat = coat;
        this.rarityLevel = rarityLevel;
        this.foodPreference = foodPreference;
        this.toyPreference = toyPreference;
    }



    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getCoat() {
        return coat;
    }

    public String getRarityLevel() {
        return rarityLevel;
    }

    public String getFoodPreference() {
        return foodPreference;
    }

    public String getToyPreference() {
        return toyPreference;
    }





}

