package model;


//represents an item, with a name and a cost
public class Item {

    protected String name;
    protected int cost;


    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }


    public String getName() {
        return name;
    }


    public int getCost() {
        return cost;
    }


}
