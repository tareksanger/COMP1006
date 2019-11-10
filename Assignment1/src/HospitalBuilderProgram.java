/**
 * Created by Tarek Sanger on 2017-01-13.
 * Student Number: 101059686
 */

// Max Distance: 26.870057685088806
public class HospitalBuilderProgram {
    public static void main(String arg[]) {
        int x, y, i, hospitalX, hospitalY, lowestX = 19, lowestY = 19, mapN = 0;
        double distance, minimax, furthestDistance = 0;

        while (mapN < 4) {
            // Makes sure values are reset for each Map
            minimax = 26.870057685088806;
            hospitalX = 0;
            hospitalY = 0;

            System.out.println("");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Map " + (mapN + 1));
            // Skips unnecessary rows/columns
            for (i = 0; i < maps[mapN].length; i++) {
                if (maps[mapN][i][0] < lowestX)
                    lowestX = maps[mapN][i][0];
                if (maps[mapN][i][1] < lowestY)
                    lowestY = maps[mapN][i][1];
            }
                // (X, Y)
            for (x = lowestX; x < 20; x++) {
                for (y = lowestY; y < 20; y++) {
                    // i = Town
                    for (i = 0; i < maps[mapN].length; i++) {
                        // Distance calculation
                        distance = Math.sqrt(Math.pow(maps[mapN][i][0] - x, 2) + Math.pow(maps[mapN][i][1] - y, 2));

                        // Furthest distance from current (x, y)
                        if (distance > furthestDistance)
                            furthestDistance = distance;
                    }
                    // Minimum furthest distance from towns on current Map
                    if (minimax > furthestDistance) {
                        minimax = furthestDistance;
                        hospitalX = x;
                        hospitalY = y;
                    }
                    // resets furthestDistance
                    furthestDistance = 0;
                }
            }
            System.out.println("Hospital location with the minimum furthest distance: (" + hospitalX + ", " + hospitalY + ")");
            System.out.println("-------------------------------------------------------------");
            mapN++;
        }
    }
    private static byte[][][] maps = {
            {{2, 2}, {2, 8}, {5, 15}, {19, 1}, {17, 17}},
            {{1, 1}, {7, 19}, {13, 19}, {19, 7}, {19, 13}},
            {{0, 19}, {2, 19}, {4, 19}, {6, 19}, {18, 19}},
            {{7, 19}, {13, 19}, {19, 19}, {19, 13}, {19, 7}}};
}