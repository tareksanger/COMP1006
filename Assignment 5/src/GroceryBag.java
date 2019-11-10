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
public class GroceryBag implements Carryable {
    protected static final double MAX_WEIGHT = 5;                                       // Max Weight
    protected static final int MAX_ITEMS = 25;                                          // Max Items
    // Attributes
    private GroceryItem[] items;
    private int numItems;
    private float weight;
    // get/set methods
    GroceryItem[] getItems() { return items;}                                            // Get Items
    public int getNumItems() { return numItems; }                                        // Get NumItems
    public float getWeight() { return weight;}                                           // Get Weight
    private float totalWeight() {                                                        // Total Weight
        float total = 0.0f;
        for (int i = 0; i < numItems; i++)
            total += items[i].getWeight();
        return total;
    }
    //GroceryItem Methods
    public String getContents() {                                                        // Get Contents
        for(int i=0; i<numItems; i++)
            System.out.println("\t  " + items[i].toString());
        return"";
    }
    public String getDescription() { return "GROCERY BAG (" + totalWeight() + "Kg)"; }  // Get Description
    public float getPrice() {                                                           // get Price
        float total = 0.0f;
        for (int i = 0; i < numItems; i++)
            total += items[i].getPrice();
        return total;
    }
    // Constructor
    GroceryBag() {
        items = new GroceryItem[MAX_ITEMS];
        numItems = 0;
        weight = 0.0f;
    }
    // toSting
    public String toString() {
        if (numItems > 0)
            return "A " + weight + "Kg Grocery Bag with " + numItems + " items";
        else
            return "An empty grocery bag";
    }
    // Method used For checking if the bag canHold the GroceryItem
    // Used from Marks A3 solutions
    boolean canHold(GroceryItem g) {                                                    // canHold
        return (((weight + g.getWeight()) <= MAX_WEIGHT) && (numItems <= MAX_ITEMS));
    }
    // Adds GroceryItem to Bag
    // Updated from Marks Solutions
    void addItem(GroceryItem g) {                                                       // Add GroceryItem
        if (canHold(g)) {
            items[numItems++] = g;
            weight += (g).getWeight();
        }
    }
    // Removes GroceryItem From Bag
    // Edits made using Marks solutions
    void removeItem(GroceryItem g) {                                                    // Remove GroceryItem
        for (int i = 0; i < numItems; i++) {
            if (items[i] == g) {
                weight -= items[i].getWeight();
                items[i] = items[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }
    // Gets heaviest item from bag
    GroceryItem heaviestItem() {                                                        // Heaviest Item
        if (numItems == 0)
            return null;
        GroceryItem heaviest = items[0];
        for (int i = 1; i < numItems; i++) {
            if (items[i].getWeight() > heaviest.getWeight())
                heaviest = items[i];
        }
        return heaviest;
    }
    // Checks back for item
    boolean has(GroceryItem item) {                                                     // Has Item
        if (numItems > 0) {
            for (int i = 0; i < numItems; i++) {
                if (items[i] == item)
                    return true;
            }
        }
        return false;
    }
    // Used from Marks solutions (made changes as instructed in part 2)
    // Remove all perishables from the bag and return an array of them
    PerishableItem[] unpackPerishables() {                                             // Unpack Perishables
        int perishableCount = 0;
        for (int i = 0; i < numItems; i++) {
            if (items[i] instanceof PerishableItem)
                perishableCount++;
        }
        PerishableItem[] perishables = new PerishableItem[perishableCount];
        perishableCount = 0;
        for (int i = 0; i < numItems; i++) {
            if (items[i] instanceof PerishableItem){
                perishables[perishableCount++] = (PerishableItem) items[i];
                removeItem(items[i]);
                i--;
            }
        }
        return perishables;
    }
}
