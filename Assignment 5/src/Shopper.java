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
public class Shopper {
    private static final int MAX_CART_ITEMS = 100;                  // Max Cart Items
    // Attributes
    private Carryable[] cart;                                       // Cart
    private int numItems;                                           // numItems
    // Constructor
    Shopper() {
        cart = new Carryable[MAX_CART_ITEMS];
        numItems = 0;
    }
    //toSting
    public String toString() {
        return "Shopper with shopping cart containing " + numItems + " items";
    }
    // Get Methods
    public Carryable[] getCart() { return cart;}                    // Get Cart
    public int getNumItems() {
        return numItems;
    }                   // Get NumItems
    // Cart Contents
    void displayCartContents() {                                    // Display Cart Contents
        for (int i = 0; i < numItems; i++) {
            System.out.println(cart[i].getDescription());
            cart[i].getContents();
        }
    }
    String computeFreezerItemCost() {
        float freezerCost = 0;
        for(int i=0; i<numItems; i++)
            if((cart[i] instanceof GroceryBag) || (cart[i] instanceof FreezerItem)) {
                if (cart[i] instanceof GroceryBag) {
                    for (int j = 0; j < ((GroceryBag)cart[i]).getNumItems(); j++)
                        if (((GroceryBag) cart[i]).getItems()[j] instanceof FreezerItem)
                            freezerCost += ((GroceryBag) cart[i]).getItems()[j].getPrice();
                } else {freezerCost += cart[i].getPrice();}
            }
        return String.format("%.2f", freezerCost);
    }
    // Total Cost of items in cart
    // Used from Marks A3 solutions (but Renamed for A4)
    String computeTotalCost() {                                      // Compute Total Cost
        float total = 0;
        for (int i = 0; i < numItems; i++) {
            total += cart[i].getPrice();
        }
        return String.format("%.2f", total);
    }
    void addItem(Carryable g) {                                   // Add Carryable
        if (numItems < MAX_CART_ITEMS)
            cart[numItems++] = g;
    }

    // Adjusted from Marks A3 Solutions
    void removeItem(Carryable item) {                             // Remove  Carryable
        for (int i = 0; i < numItems; i++) {
            if (cart[i] == item) {
                cart[i] = cart[numItems - 1];
                numItems--;
                return;
            }
        }
    }
    // Used from Marks A3 Solutions (edits made as requested for part 4 of A4)
    // Go through the shopping cart and pack all packable items into bags
    void packBags() {                                                           // Pack Bags
        GroceryBag[] packedBags = new GroceryBag[numItems];
        int bagCount = 0;
        GroceryBag currentBag = new GroceryBag();
        for (int i = 0; i < numItems; i++)
            if (((GroceryItem) cart[i]).getWeight() <= GroceryBag.MAX_WEIGHT) {
                if (!currentBag.canHold((GroceryItem) cart[i])) {
                    packedBags[bagCount++] = currentBag;
                    currentBag = new GroceryBag();
                }
                currentBag.addItem((GroceryItem) cart[i]);
                removeItem(cart[i]);
                i--;
            }
        // Check this in case there were no bagged items
        if (currentBag.getWeight() > 0)
            packedBags[bagCount++] = currentBag;
        // Adds Bags to cart
        for (int i = 0; i <bagCount; i++)
            addItem(packedBags[i]);
    }
    PerishableItem[] removePerishables() {                                          // Remove Perishables
        int perishableCount = 0;
        for (int i = 0; i < numItems; i++) {
            if (cart[i] instanceof PerishableItem || cart[i] instanceof GroceryBag)
                if (cart[i] instanceof GroceryBag) {
                    for (int j = 0; j < ((GroceryBag)cart[i]).getNumItems(); j++)
                        if (((GroceryBag) cart[i]).getItems()[j] instanceof PerishableItem)
                            perishableCount++;
                } else perishableCount++;
        }
        PerishableItem[] perishables = new PerishableItem[perishableCount];
        perishableCount = 0;
        for (int i = 0; i < numItems; i++) {
            if (cart[i] instanceof PerishableItem || cart[i] instanceof GroceryBag) {
                if (cart[i] instanceof GroceryBag) {
                    for (int j = 0; j < ((GroceryBag)cart[i]).getNumItems(); j++)
                        if (((GroceryBag) cart[i]).getItems()[j] instanceof PerishableItem) {
                            perishables[perishableCount++] = (PerishableItem) ((GroceryBag) cart[i]).getItems()[j];
                            ((GroceryBag) cart[i]).removeItem(((GroceryBag) cart[i]).getItems()[j]);
                            j--;
                        }
                } else {
                    perishables[perishableCount++] = (PerishableItem) cart[i];
                    removeItem((PerishableItem) cart[i]);
                    i--;
                }
            }
        }
        return perishables;
    }
}
