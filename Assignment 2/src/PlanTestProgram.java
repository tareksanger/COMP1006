/**
 * Created by Tarek Sanger on 2017-01-26.
 * Student Number: 101059686
 */

/* References:
'COMP1006 - Assignment #2' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
                * CODE USED FROM ASSIGNMENT 2 *
*/
public class PlanTestProgram {
    public static void main(String args[]) {
        System.out.println(new PhonePlan(200, 2500000, false));
        System.out.println(new PhonePlan(250, 500000, true));
        System.out.println(new PhonePlan(300, 5000000, false));
        System.out.println(new PhonePlan(60, 1000000, false));
        System.out.println(new PhonePlan(30, 0, true));
    }
}