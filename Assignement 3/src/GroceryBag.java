/**
 * Created by Tarek Sanger on 2017-02-04.
 * Student Number: 101059686
 */
public class GroceryBag {
    private static final double MAX_WEIGHT = 5;
    private static final int MAX_ITEMS = 25;

    // Attributes
    private GroceryItem[] items;
    private int numItems;
    private float weight;

    // get/set methods
    public GroceryItem[] getItems() { return items; }
    public int getNumItems(){ return numItems; }
    public float getWeight () { return  weight; }
    double getMaxWeight(){ return MAX_WEIGHT; }
    int getMaxItems() {return MAX_ITEMS; }


    GroceryBag(){
        items = new GroceryItem[MAX_ITEMS];
        numItems = 0;
        weight = 0.0f;
    }
    public String toString() {
        if(numItems > 0)
            return "A " + getWeight() + "Kg grocery bag with " + getNumItems() + " items";
        else
            return "An empty grocery bag";
    }
    void addItem(GroceryItem g) {
        if ((g.getItemWeight() + getWeight() < MAX_WEIGHT|| getNumItems() == 0) && (getNumItems() < MAX_ITEMS)) {
            items[numItems++] = g;
            weight += g.getItemWeight();
        }
    }
    public void removeItem(GroceryItem g) {
        for (int i = 0; i < getNumItems(); i++) {
            if (g == items[i]) {
                numItems -= 1;
                weight -= g.getItemWeight();
            }
        }
    }
    public GroceryItem heaviestItem(){
        GroceryItem heaviest = items[0];
        if (getNumItems() > 1) {
            for (int i = 1; i < getNumItems(); i++) {
                if (items[i].getItemWeight() > heaviest.getItemWeight())
                    heaviest = items[i];
            }
        return heaviest;
        } else if (getNumItems() == 1) {
            return items[0];
        } else
            return null;
    }
    public boolean has(GroceryItem item) {
        if(getNumItems() > 0) {
            for(int i = 0; i < getNumItems(); i++) {
                if (items[i].equals(item))
                    return true;
            }
        }
        return false;
    }
}
