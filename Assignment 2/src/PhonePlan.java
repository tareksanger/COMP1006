/**
 * Created by Tarek Sanger on 2017-01-26.
 * Student Number: 101059686
 */

/* References:
'COMP1006 - Assignment #2' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
public class PhonePlan {
    // Attributes
    private int minutesAllowed;
    private int minutesUsed;
    private int dataAllowed;
    private int dataUsed;
    private boolean planType;
    // Constructors
    PhonePlan(){
        minutesAllowed = 0;
        minutesUsed = 0;
        dataAllowed = 0;
        dataUsed = 0;
        planType = true;
    }
    PhonePlan(int Minutes) {
        minutesAllowed = Minutes;
        minutesUsed = 0;
        dataAllowed = 0;
        dataUsed = 0;
        planType = true;
    }
    PhonePlan(int Minutes, int Data) {
        minutesAllowed = Minutes;
        minutesUsed = 0;
        dataAllowed = Data;
        dataUsed = 0;
        planType = true;
    }
    PhonePlan(int Minutes, int Data, boolean PlanType) {
        minutesAllowed = Minutes;
        minutesUsed = 0;
        dataAllowed = Data;
        dataUsed = 0;
        planType = PlanType;
    }
    // Get Methods
    int getMinutesRemaining() { return minutesAllowed - minutesUsed; }
    int getDataRemaining() { return dataAllowed - dataUsed; }
    int getMinutesUsed() { return minutesUsed; }
    int getDataUsed() { return dataUsed; }
    int getMinutesAllowed() {return minutesAllowed;}
    int getDataAllowed() { return dataAllowed; }
    char getPlanType() {
        if(planType)
            return 'P'; // Pay-as-you-go
        else
            return 'R'; // Regular
    }
    // Set Methods
    void setMinutesUsed(int callLength) { minutesUsed += callLength; }
    void setMinutesAllowed(double minutes) { minutesAllowed += minutes; }
    void setDataUsed(int dataUsage) { dataUsed += dataUsage; }
    // toString
    public String toString(){
        if(planType)
           return "Pay-as-you-go Plan with " + getMinutesRemaining()+ " minutes and " + getDataRemaining() + "KB remaining";
        else
           return "Regular (" + minutesAllowed + " minutes, " + String.format("%.1f", dataAllowed / 1000000.0) + "GB data) "
                   + "Monthly Plan with " + getMinutesRemaining() + " minutes and " + getDataRemaining() + "KB remaining";
    }
}