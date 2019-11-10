/**
 * Created by Tarek Sanger on 2017-02-06.
 * Student Number: 101059686
 */
public class Shopper {
    private static final int MAX_CART_ITEMS = 100;

    private GroceryItem[] cart;
    private GroceryBag Bag;
    private GroceryBag[] Bags;
    private int numItems;

    public GroceryItem[] getCart() {
        return cart;
    }

    public int getNumItems() {
        return numItems;
    }

    Shopper() {
        cart = new GroceryItem[MAX_CART_ITEMS];
        numItems = 0;
    }

    public String toString() {
        return "Shopper with shopping cart containing " + getCart() + " items";
    }

    public void addItem(GroceryItem g) {
        if ((numItems >= 0) && (numItems < MAX_CART_ITEMS)) {
            cart[numItems++] = g;
        }
    }

    public void removeItem(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (cart[i] == item) {
                cart[i] = cart[numItems - 1];
                cart[numItems - 1] = null;
                numItems--;
                i--;
            }
        }
    }
}

