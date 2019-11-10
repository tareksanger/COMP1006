/**
 * Created by Tarek Sanger on 2017-02-09.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #3' - by Mark Lanthier (Used code from Specifications as instructed)
'COMP1006 - Assignment #4' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
                * Some Code used from Marks A3 Solutions *
*/
public class GroceryItem implements Carryable {
    // Attributes
    protected String name;
    protected float price;
    protected float weight;
    // Constructors
    GroceryItem() {
        name = "UNKNOWN";    // Not used
        price = 0.0f;
        weight = 0.0f;
    }
    GroceryItem(String Name, float Price, float Weight) {
        name = Name;
        price = Price;
        weight = Weight;
    }
    // get/set methods
    public String getName(){ return name; }                             // Get Name
    public float getWeight(){ return weight; }                          // Get Weight
    //Carryable Methods
    public String getContents() { return ""; }                          // Get Contents
    public String getDescription() { return name; }                     // Get Description
    public float getPrice() { return price; }                           // Get Price
    // toString
    public String toString(){
        return getName() + " weighing " + weight + "Kg with price " + String.format("$%.2f", price);
    }
}