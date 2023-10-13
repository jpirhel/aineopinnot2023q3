package org.example.logic;

import org.example.data.Airport;
import org.example.data.AirportDistance;
import org.example.data.AirportGraph;

import java.util.*;

public class DijkstraSearch {
    private final ArrayList<Airport> airports;
    private final int[][] airportDistances;

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
    public int[] search(
            int startAirportId,
            int destAirportId,
            int maxDistance,
            AirportGraph airportGraph) {
        int[] directDistances = airportDistances[startAirportId];

        int numAirports = directDistances.length;

        // check if the plane can fly directly to the destination

        int distanceBetweenStartAndDest = directDistances[destAirportId];

        int[] path = new int[numAirports];
        int currentPathPosition = 0;

        if (distanceBetweenStartAndDest <= maxDistance) {
            // the path contains only a direct flight to the destination airport
            path[currentPathPosition] = destAirportId;

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

        PriorityQueue<AirportDistance> Q = new PriorityQueue<>();

        // start airport

        Airport startAirport = airports.get(startAirportId);
        AirportDistance start = new AirportDistance(startAirport, startAirport, 0);

        Q.add(start);

        ArrayList<AirportDistance>[] graph = airportGraph.getGraph();

        // implement Dijkstra's algorithm as per [1]
        // [1]: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue

        while (! Q.isEmpty()) {
            AirportDistance curr = Q.poll();

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
                    Q.add(next);
                }
            }
        }

        int currId = destAirportId;

        // check if there is a path
        try {
            currId = prev[currId];
        } catch (Exception ex) {
            return null; // No path found
        }

        // re-initialize after check

        currId = destAirportId;

        int pathPosition = 1;

        // add destination airport to path

        path[0] = destAirportId;

        while (true) {
            if (pathPosition > 100) {
                break;
            }

            try {
                currId = prev[currId];
            } catch (Exception ex) {
                System.out.println(Arrays.toString(ex.getStackTrace()));
                break;
            }

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
    public ArrayList<Airport> normalizedSearch(int startAirportId, int destAirportId, int testRangeInKm, AirportGraph airportGraph) {
        int[] path = search(startAirportId, destAirportId, testRangeInKm, airportGraph);

        if (path == null) {
            return null;
        }

        ArrayList<Airport> normalizedPath = new ArrayList<>();

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
