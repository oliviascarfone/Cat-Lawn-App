package model;


//represents an item, with a name and a cost
public abstract class Item {

    protected String name;
    protected int cost;

    public Item() {
    }


    public String getName() {
        return name;
    }


    public int getCost() {
        return cost;
    }


}
