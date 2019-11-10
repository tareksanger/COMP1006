/**
 * Created by Tarek Sanger on 2017-02-10.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #3' - by Mark Lanthier (Used code from Specifications as instructed)
'COMP1006 - Assignment #4' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
public abstract class PerishableItem extends GroceryItem {
    // Attribute
    protected boolean perishable;
    //Constructor
    PerishableItem(String Name, float Price, float Weight, boolean Perish) {
        super(Name, Price, Weight);
        perishable = Perish;
    }
    PerishableItem(String Name, float Price, float Weight) {
        super(Name, Price, Weight);
        perishable = true;
    }
    public PerishableItem() {
        super("UNKNOWN", 0.0f, 0.0f);   // Not Used
        perishable = true;
    }
    // toString Method
    public String toString() { return super.toString() + " (perishable) "; }
}
