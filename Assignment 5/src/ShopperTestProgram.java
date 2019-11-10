/**
 * Created by Tarek Sanger on 2017-02-09.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #3' - by Mark Lanthier (Used code from Specifications as instructed)
'COMP1006 - Assignment #4' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
public class ShopperTestProgram {
    public static void main(String args[]) {
        GroceryItem g2, g4, g5, g6, g7, g10, g11;
        FreezerItem g1, g3, g9, g15;
        RefrigeratorItem g8, g12, g13, g14;
        g1 = new FreezerItem("Smart-Ones Frozen Entrees",1.99f,0.311f, true);
        g2 = new GroceryItem("SnackPack Pudding",0.99f,0.396f);
        g3 = new FreezerItem("Breyers Chocolate Icecream",2.99f,2.27f, true);
        g4 = new GroceryItem("Nabob Coffee",3.99f,0.326f);
        g5 = new GroceryItem("Gold Seal Salmon",1.99f,0.213f);
        g6 = new GroceryItem("Ocean Spray Cranberry Cocktail",2.99f,2.26f);
        g7 = new GroceryItem("Heinz Beans Original",0.79f,0.477f);
        g8 = new RefrigeratorItem("Lean Ground Beef",4.94f,0.75f, true);
        g9 = new FreezerItem("5-Alive Frozen Juice",0.75f,0.426f, true);
        g10 = new GroceryItem("Coca-Cola 12-pack",3.49f,5.112f);
        g11 = new GroceryItem("Toilet Paper - 48 pack",40.96f,10.89f);
        g12 = new RefrigeratorItem("2L Sealtest Milk",2.99f,2.06f);
        g13 = new RefrigeratorItem("Extra-Large Eggs",1.79f,0.77f);
        g14 = new RefrigeratorItem("Yoplait Yogurt 6-pack",4.74f,1.02f);
        g15 = new FreezerItem("Mega-Sized Chocolate Icecream",67.93f,15.03f);

        // Make a new customer and add some items to his/her shopping cart
        Shopper c = new Shopper();
        c.addItem(g1); c.addItem(g2); c.addItem(g3); c.addItem(g4);
        c.addItem(g5); c.addItem(g6); c.addItem(g7); c.addItem(g8);
        c.addItem(g9); c.addItem(g10); c.addItem(g1); c.addItem(g6);
        c.addItem(g2); c.addItem(g2); c.addItem(g3); c.addItem(g3);
        c.addItem(g3); c.addItem(g3); c.addItem(g3); c.addItem(g10);
        c.addItem(g11); c.addItem(g9); c.addItem(g5); c.addItem(g6);
        c.addItem(g7); c.addItem(g8); c.addItem(g8); c.addItem(g8);
        c.addItem(g5); c.addItem(g12); c.addItem(g13); c.addItem(g14);
        c.addItem(g15);
        System.out.println("\nINITIAL CART CONTENTS:");
        for (int i=0; i<c.getNumItems(); i++) {
            System.out.println(" " + c.getCart()[i]);
        }
        // Pack the bags and show the contents
        c.packBags();
        System.out.println("\nCART CONTENTS");
        c.displayCartContents();
        System.out.println("\nTOTAL FREEZER ITEM COST: $" + c.computeFreezerItemCost());
        System.out.println("\nTOTAL CART CONTENTS COST: $" + c.computeTotalCost());
        System.out.println("\nREMAINING CART CONTENTS:");
        for (int i=0; i<c.getNumItems(); i++) {
            System.out.println(" " + c.getCart()[i]);
        }
        System.out.println("\nUNPACKING PERISHABLES:");
        for (int i=0; i<c.getNumItems(); i++) {
            GroceryItem[] perishables = c.removePerishables();
            for (GroceryItem perishable : perishables) {
                System.out.println(" " + perishable);
            }
        }
        System.out.println("\nREMAINING CART CONTENTS:");
        c.displayCartContents();
    }
}