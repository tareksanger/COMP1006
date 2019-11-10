/**
 * Created by Tarek Sanger on 2017-01-13.
 * Student Number: 101059686
 */

import java.util.Scanner;


public class WaterLevelProgram {
    public static void main(String ars[]) {
        int oldMM, count, mm;

        count = 0;
        oldMM = 0;

        while (count != 3) {

            System.out.print("What is the water level at now (in mm): ");
            mm = new Scanner(System.in).nextInt();

            if ((count >= 1) && (oldMM <= mm)) {
                count = 0;
            } else {
                oldMM = mm;
                count++;
            }
        }

        System.out.println(" ");
        System.out.println("It appears that the flood is subsiding.");

    }
}
