/**
 * Created by Tarek Sanger on 2017-01-24.
 * Student Number: 101059686
 */

/* References:
'COMP1006 - Assignment #2' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/

public class CellPhone {
    //Attributes
    private String model;
    private String manufacturer;
    private int monthsWarranty;
    private float price;
    // Constructors
    CellPhone(){
        model = "UNKNOWN";
        manufacturer = "UNKNOWN";
        monthsWarranty = 0;
        price = 0.0f;
    }
    CellPhone(String Model) {
        model = Model;
        manufacturer = "UNKNOWN";
        monthsWarranty = 0;
        price = 0.0f;
    }
    CellPhone(String Model, String Manufacturer) {
        model = Model;
        manufacturer = Manufacturer;
        monthsWarranty = 0;
        price = 0.0f;
    }
    CellPhone(String Model, String Manufacturer, int Warranty) {
        model = Model;
        manufacturer = Manufacturer;
        monthsWarranty = Warranty;
        price = 0.0f;
    }
    CellPhone(String Model, String Manufacturer, int Warranty, float Price){
        model = Model;
        manufacturer = Manufacturer;
        monthsWarranty = Warranty;
        price = Price;
    }
    // Get Methods
    String getModel() { return model; }
    String getManufacturer() { return manufacturer;}
    int getMonthsWarranty() { return monthsWarranty; }
    float getPrice(){ return price; }
    // Set Methods
    String setModel(String m) { return model = m; }
    // Warranty Length Check between three Phones
    boolean warrantyIsLonger(CellPhone x, CellPhone z) { return (monthsWarranty > x.monthsWarranty) && (monthsWarranty > z.monthsWarranty);}
    // Total Cost of Three Phones
    float totalCostThreePhones(CellPhone c, CellPhone x){
        return price + c.price + x.price;
    }
}