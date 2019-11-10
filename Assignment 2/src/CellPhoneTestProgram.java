/**
 * Created by Tarek Sanger on 2017-01-24.
 * Student Number: 101059686
 */

/* References:
'COMP1006 - Assignment #2' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
                * CODE USED FROM ASSIGNMENT 2 *
*/
public class CellPhoneTestProgram {
    public static void main(String args[]) {
        // Create three CellPhone objects.
    // One should be an "iPhone6Plus" cell from Apple which has a 12 month warranty and costs
    // $915. Another cell should be a "Galaxy S7" from Samsung with an 18 month warranty, and
    // a price of $900.00. The last phone should be a "PRIV" from BlackBerry with a 24 month
    // warranty and a price of $890.00.
        CellPhone iPhone = new CellPhone("iPhone6Plus", "Apple", 12, 915.00f);
        CellPhone galaxy = new CellPhone("Galaxy S7", "Samsung", 18, 900.00f);
        CellPhone priv = new CellPhone("PRIV", "BlackBerry", 24, 890.00f);
        System.out.println("Here is the Apple phone information:");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(iPhone.getPrice());
        System.out.println("\nHere is the Samsung phone information:");
        System.out.println(galaxy.getModel());
        System.out.println(galaxy.getManufacturer());
        System.out.println(galaxy.getMonthsWarranty());
        System.out.println(galaxy.getPrice());
        System.out.println("\nHere is the BlackBerry phone information:");
        System.out.println(priv.getModel());
        System.out.println(priv.getManufacturer());
        System.out.println(priv.getMonthsWarranty());
        System.out.println(priv.getPrice());
        // Write code to change the Apple phone's
        // model to "iPhoneSE" and its price to $590.00
        // ... WRITE CODE HERE...
        iPhone.setModel("iPhoneSE");
        System.out.println("\nHere is the Apple phone's new information:");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(iPhone.getPrice());
        // Complete the line below so that it shows the combined total
        // of all three phone prices
        System.out.println("The total cost of all phones is $" + iPhone.totalCostThreePhones(galaxy, priv));
        // Complete the code below so that it determines which phone has the longest
        // warranty and displays the result using a nice readable sentence
        if (iPhone.warrantyIsLonger(galaxy, priv))
            System.out.println("The Apple phone has the longest warranty");
        else if (priv.warrantyIsLonger(galaxy, iPhone))
            System.out.println("The BlackBerry phone has the longest warranty");
        else
            System.out.println("The Samsung phone has the longest warranty");

    }
}