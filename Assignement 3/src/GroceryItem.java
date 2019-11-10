/**
 * Created by Tarek Sanger on 2017-02-04.
 * Student Number: 101059686
 */
public class GroceryItem {
    // Attributes
    private String name;
    private float price;
    private float weight;
    private boolean perishable;

    // Constructor
    GroceryItem() {
        name = "UNKNOWN";
        price = 0.0f;
        weight = 0.0f;
        perishable = false;
    }
    GroceryItem(String Name, float Price, float Weight) {
        name = Name;
        price = Price;
        weight = Weight;
        perishable = false;
    }
    GroceryItem(String Name, float Price, float Weight, boolean p) {
        name = Name;
        price = Price;
        weight = Weight;
        perishable = p;
    }
    // get/set methods
    public String getItemName(){ return name; }
    public float getItemPrice(){ return price; }
    public float getItemWeight(){ return weight; }
    public boolean getPerishable(){ return perishable; }


    public String toString(){
        return getItemName() + " weighing " + getItemWeight() + "Kg with price " + String.format("$%.2f", getItemPrice());
    }


}
