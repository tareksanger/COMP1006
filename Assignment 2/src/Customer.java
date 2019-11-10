/**
 * Created by Tarek Sanger on 2017-01-26.
 * Student Number: 101059686
 */

/* References:
'COMP1006 - Assignment #2' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
public class Customer {
    // Attributes
    private String name;
    private CellPhone cellPhone;
    private PhonePlan plan;
    private int callsMade;
    private float balance;
    // Constructors
    Customer() {
        name = "UNKOWN";
        cellPhone = null;
        plan =  null;
        callsMade = 0;
        balance = 0;
    }
    Customer(String Name) {
        name = Name;
        cellPhone = null;
        plan =  null;
        callsMade = 0;
        balance = 0;
    }
    Customer(String Name, CellPhone Phone) {
        name = Name;
        cellPhone = Phone;
        plan =  null;
        callsMade = 0;
        balance = 0;
    }
    Customer(String Name, CellPhone Phone, PhonePlan Plan) {
        name = Name;
        cellPhone = Phone;
        plan = Plan;
        callsMade = 0;
        balance = 0;
    }
    // toString
    public String toString(){
        return name + " with a " + cellPhone.getManufacturer() + " " + cellPhone.getModel() + " on a " + plan.toString();
    }
    // Get & Set
    PhonePlan getPlan() { return plan; }
    // Phone call Method
    void phone(Customer c, int callLength) {
        if( (plan.getPlanType() =='R' || (plan.getPlanType() == 'P' && getPlan().getMinutesRemaining() >= callLength)) &&
                (c.plan.getPlanType() == 'R' || (c.plan.getPlanType() == 'P' && c.getPlan().getMinutesRemaining() >= callLength))) {
            callsMade += 1;
            plan.setMinutesUsed(callLength);
            c.callsMade += 1;
            c.plan.setMinutesUsed(callLength);
        }
    }
    // Buy Minutes Method
    void buyMinutes(int minutesPurchased) {
        if(plan.getPlanType() == 'P')
            balance += 0.40 * minutesPurchased;
            plan.setMinutesAllowed(minutesPurchased);
    }
    // Internet Access Method
    void accessInternet(int dataUsage) {
        if (plan.getDataAllowed() > 0) {
            if (plan.getPlanType() == 'P') {
                if (dataUsage > (plan.getDataAllowed() - plan.getDataUsed()))
                    plan.setDataUsed(plan.getDataAllowed() - plan.getDataUsed());
            } else
                plan.setDataUsed(dataUsage);
        }
    }
    // Monthly Statement
    void printMonthlyStatement() {
        double monthlyCharges = 0;
        double voiceOvertimeCharges = 0;
        double dataOverUsageCharges = 0;
        float hst = 0.13f;
        System.out.println();
        System.out.println(String.format("%-25s", "Name:") + name);
        if (plan.getPlanType() == 'P') {
            // Pay-as-you-go Phone Bill
            System.out.println(String.format("%-25s", "Plan Type:") + "Pay-as-you-go");
            System.out.println(String.format("%-25s", "Minutes Used:") + plan.getMinutesUsed());
            System.out.println(String.format("%-25s", "Minutes Remaining:") + plan.getMinutesRemaining());
            System.out.println(String.format("%-25s", "Data Used:") + plan.getDataUsed());
            System.out.println(String.format("%-25s", "Data Used:") + plan.getDataRemaining());
            System.out.println(String.format("%-25s", "Calls Made:") + callsMade);
            monthlyCharges += (plan.getMinutesAllowed() * 0.40);
            System.out.println(String.format("%-25s$%,1.2f", "Monthly Charges:", monthlyCharges));
        } else {
            // Regular Phone bill
            // Data, Monthly Charges
            if (plan.getDataUsed() > 0) {
                for (int gb = 0; gb < plan.getDataAllowed(); gb += 500000)
                    monthlyCharges += 5;
            }
            // Minutes calculator
            if (plan.getMinutesAllowed() == 200)
                monthlyCharges += 25;
            else
                monthlyCharges += 15;
            System.out.println(String.format("%-25s", "Plan Type:") + "Regular (" + plan.getMinutesAllowed() + " minutes, "
                    + String.format("%.1f", plan.getDataAllowed() / 1000000.0) + "GB data) ");
            System.out.println(String.format("%-25s", "Minutes Used:") + plan.getMinutesUsed());
            System.out.println(String.format("%-25s", "Data Used:") + plan.getDataUsed());
            System.out.println(String.format("%-25s", "Calls Made:") + callsMade);
            System.out.println(String.format("%-25s$%,1.2f", "Monthly Charges:", monthlyCharges));
            // Voice Overtime Chargers
            if (plan.getMinutesAllowed() < plan.getMinutesUsed())
                voiceOvertimeCharges = 0.15 * (plan.getMinutesUsed() - plan.getMinutesAllowed());
            System.out.println(String.format("%-25s$%,1.2f", "Voice Overtime Charges:", voiceOvertimeCharges));
            // Data Over Usage Charges
            if (plan.getDataAllowed() < plan.getDataUsed())
                dataOverUsageCharges = 0.00005 * (plan.getDataUsed() - plan.getDataAllowed());
            System.out.println(String.format("%-25s$%,1.2f", "Data Over Usage Charges:", dataOverUsageCharges));
        }
        // HST & total
        System.out.println(String.format("%-25s$%,1.2f", "HST:", hst*= (monthlyCharges + voiceOvertimeCharges + dataOverUsageCharges)));
        System.out.println(String.format("%-25s$%,1.2f", "Total Due:", (monthlyCharges+ voiceOvertimeCharges + dataOverUsageCharges + hst)));
        System.out.println();
    }
}