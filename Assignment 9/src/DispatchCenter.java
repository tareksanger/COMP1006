/**
 * Created by Tarek Sanger on 2017-03-24.
 * Student Number: 101059686
 */
import java.util.*;

public class DispatchCenter {
    public static String[] AREA_NAMES = {"Downtown", "Airport", "North", "South", "East", "West"};

    private int[][]  stats; // You'll need this for the last part of the assignment

    private HashMap<Integer, Taxi>              taxis;
    private HashMap<String, ArrayList<Taxi>>    areas;

    // Constructor
    public DispatchCenter() {
        // You'll need this for the last part of the assignment
        stats = new int[AREA_NAMES.length][AREA_NAMES.length];

        taxis = new HashMap<Integer, Taxi>();
        areas = new HashMap<String, ArrayList<Taxi>>();

        HashSet<Taxi> cabbieList = new HashSet<>();
        while (cabbieList.size() < 55 ) {
            for (int i = 0; i < 10; i++)
                cabbieList.add(new Taxi(randomWithRange(100, 999)));
        }
            for (Taxi t: cabbieList) {
                addTaxi(t, AREA_NAMES[(int) (Math.random() * 6)]);
                if(taxis.size() == 50)
                    break;
            }
    }

    // You'll need this for the last part of the assignment
    public int[][]   getStats() { return stats; }
    public HashMap   getAreas() {return areas; }


    // Update the statistics for a taxi going from the pickup location to the dropoff location
    public void updateStats(String pickup, String dropOff) {
        int from = 0;
        int to = 0;
        for(int i=0; i<AREA_NAMES.length; i++) {
            if (pickup.equals(AREA_NAMES[i]))
                from = i;
            if (dropOff.equals(AREA_NAMES[i]))
                to = i;
        }
        stats[to][from] += 1;
    }
    // Determine the travel times from one area to another
    public static int computeTravelTimeFrom(String pickup, String dropOff) {
                                //   DT  AP   N   S   E   W
        int[][] travelTimeGraph = { {10, 40, 20, 20, 20, 20},  //DT
                                    {40, 10, 40, 40, 20 ,60},  //AP
                                    {20, 40, 10, 40, 20, 20},  //N
                                    {20, 40, 40, 10, 20, 20},  //S
                                    {20, 20, 20, 20, 10, 40},  //E
                                    {20, 60, 20, 20, 40, 10}}; //W
        int from = 7;
        int to = 7;
        for(int i=0; i<AREA_NAMES.length; i++) {
            if (pickup.equals(AREA_NAMES[i]))
                from = i;
            if (dropOff.equals(AREA_NAMES[i]))
                to = i;
        }
        return travelTimeGraph[to][from];
    }
    // Add a taxi to the hashmaps
    public void addTaxi(Taxi aTaxi, String area) {
        taxis.put(aTaxi.getPlateNumber(), aTaxi);

        if(!areas.containsKey(area))
            areas.put(area, new ArrayList<Taxi>());

        aTaxi.setDestination(area);
        areas.get(area).add(aTaxi);
    }

    // Return a list of all available taxis within a certain area
    private ArrayList<Taxi> availableTaxisInArea(String s) {
        ArrayList<Taxi> result = new ArrayList<Taxi>();

        for(Taxi t: areas.get(s))
            if(t.getAvailable())
                result.add(t);

        return result;
    }
    // Return a list of all busy taxis
    public ArrayList<Taxi> getBusyTaxis() {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for(int i=0; i<AREA_NAMES.length; i++)
            for(Taxi t: areas.get(AREA_NAMES[i]))
                if(!(t.getAvailable()))
                    result.add(t);
        return result;
    }
    // Find a taxi to satisfy the given request
    public Taxi sendTaxiForRequest(ClientRequest request) {
        PriorityQueue<Taxi> incomingCabbies = new PriorityQueue<Taxi>(50);
        if (incomingCabbies.isEmpty())
            for (String AREA_NAME : AREA_NAMES)
                for (Taxi t : areas.get(AREA_NAME))
                    if (t.getAvailable()) {
                        t.setEstimatedTimeToDest(computeTravelTimeFrom(t.getDestination(), request.getPickupLocation()) + computeTravelTimeFrom(request.getPickupLocation(), request.getDropoffLocation()));
                        incomingCabbies.add(t);
                    }
        // Change taxi with shortest time to unavailable and remove him from current area and add him to dest area
        Taxi nearestTaxi = null;
        if (!incomingCabbies.isEmpty()) {
            areas.get(incomingCabbies.peek().getDestination()).remove(incomingCabbies.peek());
            incomingCabbies.peek().setDestination(request.getDropoffLocation());
            areas.get(incomingCabbies.peek().getDestination()).add(incomingCabbies.peek());
            nearestTaxi = incomingCabbies.remove();
            nearestTaxi.setAvailable(false);
        }
        // resets all of the remaining taxis EstimatedTimeToDest
        while (!incomingCabbies.isEmpty()) {
            incomingCabbies.remove().setEstimatedTimeToDest(0);
        }
        if(nearestTaxi != null)
            updateStats(request.getPickupLocation(), request.getDropoffLocation());
        return nearestTaxi;
    }

    // Random() with range method.
    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
