package org.example.logic;

import java.util.*;

import org.example.data.Airport;
import org.example.data.AirportDistance;
import org.example.data.AirportGraph;

public class DijkstraSearch {
    private final ArrayList<Airport> airports;
    private final int[][] airportDistances;


    /**
     * Implementation of Dijkstra's algorithm to find shortest path between airports.
     *
     * Uses a priority queue.
     *
     * @param airports
     * @param airportDistances
     */
    public DijkstraSearch(ArrayList<Airport> airports, int[][] airportDistances) {
        this.airports = airports;
        this.airportDistances = airportDistances;
    }

    /**
     * Dijkstra algorithm search of shortest path between two airports.
     *
     * @param startAirportId
     * @param destAirportId
     * @param maxDistance
     * @param airportGraph
     * @return path of airports between start and destination
     */
    private int[] search(
            int startAirportId,
            int destAirportId,
            int maxDistance,
            AirportGraph airportGraph) {
        int[] directDistances = airportDistances[startAirportId];

        int numAirports = directDistances.length;

        // check if the plane can fly directly to the destination

        int distanceBetweenStartAndDest = directDistances[destAirportId];

        System.out.println("distanceBetweenStartAndDest: " + distanceBetweenStartAndDest);
        System.out.println("maxDistance: " + maxDistance);

        int[] path = new int[numAirports];

        if (distanceBetweenStartAndDest <= maxDistance) {
            // the path contains only a direct flight to the destination airport

            path[0] = startAirportId;
            path[1] = destAirportId;

            return path;
        }

        // array of distances calculated by the Dijkstra algorithm
        Integer[] dist = new Integer[numAirports];

        // initialize distance array to "positive infinity"
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[startAirportId] = 0; // the path length to the start airport itself

        // pointers to the previous airport in the path
        Integer[] prev = new Integer[numAirports];

        // priority queue to hold distance-weighted airports in the search queue

        PriorityQueue<AirportDistance> queue = new PriorityQueue<>();

        // start airport

        Airport startAirport = airports.get(startAirportId);
        AirportDistance start = new AirportDistance(startAirport, startAirport, 0);

        queue.add(start);

        ArrayList<AirportDistance>[] graph = airportGraph.getGraph();

        // implement Dijkstra's algorithm as per [1]
        // [1]: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue

        while (! queue.isEmpty()) {
            AirportDistance curr = queue.poll();

            int fromId = curr.getToAirport().getId();
            Airport fromAirport = curr.getToAirport();

            ArrayList<AirportDistance> neighbours = graph[fromId];

            int currDistance = dist[fromId];

            for (AirportDistance neighbour: neighbours) {
                int toId = neighbour.getToAirport().getId();
                Airport toAirport = neighbour.getToAirport();

                int airportDistance = airportDistances[fromId][toId];
                int altDistance = currDistance + airportDistance;

                if (altDistance < dist[toId]) {
                    dist[toId] = altDistance;
                    prev[toId] = fromId;

                    AirportDistance next = new AirportDistance(fromAirport, toAirport, altDistance);
                    queue.add(next);
                }
            }
        }

        int currId = destAirportId;

        // check if there is a path
        try {
            currId = prev[currId];
        } catch (NullPointerException ex) {
            return null; // No path found
        }

        // re-initialize after check

        currId = destAirportId;

        int pathPosition = 1;

        // add destination airport to path

        path[0] = destAirportId;

        while (true) {
            try {
                currId = prev[currId];
            } catch (NullPointerException ex) {
                return null; // No path found
            }

            // destination found - path ends

            if (currId == startAirportId) {
                break;
            }

            // add next hop to path

            path[pathPosition] = currId;
            pathPosition += 1;
        }

        // add destination airport to path

        path[pathPosition] = startAirportId;

        return path;
    }

    /**
     * Dijkstra search of path between airports.
     *
     * @param startAirportId
     * @param destAirportId
     * @param testRangeInKm
     * @param airportGraph
     * @return ArrayList of path airports
     */
    public ArrayList<Airport> normalizedSearch(
            int startAirportId,
            int destAirportId,
            int testRangeInKm,
            AirportGraph airportGraph) {
        // search using own implementation of Dijkstra's algorithm

        int[] path = search(
                startAirportId,
                destAirportId,
                testRangeInKm,
                airportGraph);

        // check if path is empty

        if (path == null) {
            return null;
        }

        ArrayList<Airport> normalizedPath = new ArrayList<>();

        // add airports to ArrayList - break on zero (path ends here)

        for (int airport : path) {
            if (airport == 0) {
                break;
            }

            normalizedPath.add(airports.get(airport));
        }

        Collections.reverse(normalizedPath);

        return normalizedPath;
    }
}
