package org.example.logic;

//CHECKSTYLE.OFF: AvoidStarImport

import java.util.*;

import org.example.data.Airport;
import org.example.data.AirportData;
import org.example.data.AirportDistance;
import org.example.data.AirportGraph;

/**
 * Implementation of the IDA* search algorithm
 */
public class IdastarSearch {
    private static final Integer TIMEOUT = -2;
    private static final Integer FOUND = -1;
    private static final Integer NOT_FOUND = Integer.MAX_VALUE;
    private static final Double TIMEOUT_VALUE = 20000000000D; // 20 seconds
    private final AirportData airportData;
    private final int[][] airportDistances;
    private int iterationCount;
    private Airport startAirport;
    private AirportGraph airportGraph;
    private int destAirportId;
    private long startTime;

    /**
     * Constructor for the IDA* search object.
     * @param airportData The Airport data object
     */
    public IdastarSearch(AirportData airportData) {
        this.iterationCount = 0;
        this.airportData = airportData;
        this.airportDistances = airportData.getAirportDistances();
    }

    /**
     * The main public IDA* search function.
     * @param startAirport The Start airport object
     * @param destAirport  The Destination airport object
     * @param airportGraph The airport graph
     * @return The found path in normalized form
     */
    public ArrayList<Airport> normalizedSearch(
            Airport startAirport,
            Airport destAirport,
            AirportGraph airportGraph) {
        this.startAirport = startAirport;

        this.destAirportId = destAirport.getId();

        this.airportGraph = airportGraph;

        startTime = System.nanoTime();

        ArrayList<Airport> path = idaStar();

        long endTime = System.nanoTime();

        long elapsed = endTime - startTime;

        long elapsedInS = elapsed / 1000000000;

        System.out.println("*** elapsed seconds: " + elapsedInS);
        System.out.println("*** iterationCount:  " + iterationCount);

        return path;
    }

    /**
     * The outer IDA* algorithm search function.
     * @return The path of airports from start to destination.
     */
    private ArrayList<Airport> idaStar() {
        // IDA* search implemented in Java. Original: https://en.wikipedia.org/wiki/Iterative_deepening_A*#Pseudocode

        Airport start = startAirport;

        Integer treshold = heuristic(start);

        ArrayList<Airport> path = new ArrayList<>();

        // add starting airport to path
        path.add(start);

        while (true) {
            Integer t = idaSearch(path, 0, treshold);

            // path found
            if (Objects.equals(t, IdastarSearch.FOUND)) {
                return path;
            }

            // path not found
            if (Objects.equals(t, IdastarSearch.NOT_FOUND)) {
                return null;
            }

            // search timeout
            if (Objects.equals(t, IdastarSearch.TIMEOUT)) {
                System.out.println("Search timeout!");
                return null;
            }

            treshold = t;
        }
    }

    /**
     * Inner search function of the IDA* algorithm.
     *
     * @param path The current path to be explored
     * @param g The current cost
     * @param threshold The current treshold
     * @return Integer representing found path, next treshold, path not found or timeout
     */
    private Integer idaSearch(ArrayList<Airport> path, Integer g, Integer threshold) {
        iterationCount += 1;

        if (iterationCount % 1000000 == 0) {
            long currTime = System.nanoTime();
            long elapsedTime = currTime - startTime;

            int elapsedTimePrintable = Math.toIntExact(elapsedTime / 1000000000);

            //CHECKSTYLE.OFF: LineLength
            System.out.println("elapsed time: " + elapsedTimePrintable + ", iterations: " + iterationCount);
            //CHECKSTYLE.ON: LineLength

            // check timeout

            if (elapsedTime > IdastarSearch.TIMEOUT_VALUE) {
                return IdastarSearch.TIMEOUT;
            }
        }

        // current examined node is the last airport on the path
        Airport node = path.get(path.size() - 1);

        // calculate heuristic for current node
        int h = heuristic(node);

        // if heuristic (distance) is zero, we're at the destination airport
        if (h == 0) {
            return IdastarSearch.FOUND;
        }

        // total cost is current cost plus heuristic
        Integer f = g + h;

        // do not continue if total cost is over the threshold

        if (f > threshold) {
            return f;
        }

        //noinspection WrapperTypeMayBePrimitive
        Integer min = Integer.MAX_VALUE;

        ArrayList<AirportDistance>[] graph = airportGraph.getGraph();

        int fromId = node.getId();

        ArrayList<AirportDistance> neighbours = graph[fromId];

        // sort neighbours by distance
        Collections.sort(neighbours);

        for (AirportDistance neighbour : neighbours) {
            Airport nextAirport = neighbour.getToAirport();

            if (!path.contains(nextAirport)) {
                path.add(nextAirport);

                // distance to next airport
                Integer nextDistance = airportDistances[node.getId()][nextAirport.getId()];

                // next cost is current cost plus distance to next airport
                Integer nextCost = g + nextDistance;

                Integer t = idaSearch(path, nextCost, threshold);

                if (t.equals(FOUND)) {
                    return FOUND;
                }

                if (t < min) {
                    min = t;
                }

                path.remove(path.size() - 1);  // remove last element
            }
        }

        return min;
    }

    /**
     * Heuristic function for estimating cost to destination airport.
     * <p>
     * The straight line distance will always be lower than the real path, so it is admissible [1].
     * [1]: https://en.wikipedia.org/wiki/Iterative_deepening_A*#Properties
     *
     * @param curr Current airport
     * @return distance to destination airport
     */
    private int heuristic(Airport curr) {
        //noinspection UnnecessaryLocalVariable
        int distance = airportDistances[curr.getId()][this.destAirportId];

        return distance;
    }
}
