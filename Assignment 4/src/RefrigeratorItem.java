/**
 * Created by Tarek Sanger on 2017-02-10.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #3' - by Mark Lanthier (Used code from Specifications as instructed)
'COMP1006 - Assignment #4' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
public class RefrigeratorItem extends PerishableItem {
    // Constructors
    RefrigeratorItem(String Name, float Price, float Weight, boolean Perish) { super(Name, Price, Weight, Perish); }
    RefrigeratorItem(String Name, float Price, float Weight) { super(Name, Price, Weight); }
    // toString Method
    public String toString() { return super.toString() + "[keep refrigerated]"; }
}