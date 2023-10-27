package org.example.data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.example.geo.GeoUtil;

/**
 * Generates various data relating to airports.
 */
public class AirportDataGenerator {
    private final ArrayList<Airport> airports;
    private HashMap<Integer, Airport> airportMap;
    private int[][] airportDistances;

    private AirportData airportData;
    private HashMap<String, Airport> icaoIndex;

    /**
     * @param airports The list of airports
     */
    public AirportDataGenerator(ArrayList<Airport> airports) {
        this.airports = airports;

        generateAirportMapAndIcaoIndex();
        generateDistanceArray();

        //noinspection UnnecessaryLocalVariable
        AirportData airportData = new AirportData(
                airports,
                airportMap,
                airportDistances,
                icaoIndex);

        this.airportData = airportData;
    }

    /**
     * Airport data
     * @return Airport data object
     */
    public AirportData getAirportData() {
        return airportData;
    }

    /**
     * Generate Airport HashMap and ICAO index
     */
    private void generateAirportMapAndIcaoIndex() {
        airportMap = new HashMap<>();
        icaoIndex = new HashMap<>();

        for (Airport airport : airports) {
            airportMap.put(airport.getId(), airport);
            icaoIndex.put(airport.getIcao(), airport);
        }
    }

    /**
     * Generates the airport distance array
     */
    private void generateDistanceArray() {
        int count = airportMap.size();

        int[][] distances = new int[count][count];

        for (Airport airportFrom : airports) {
            int idFrom = airportFrom.getId();

            for (Airport airportTo : airports) {
                int idTo = airportTo.getId();

                if (idFrom == idTo) {
                    // airport's distance to itself
                    distances[idFrom][idTo] = 0;
                    continue;
                }

                if (distances[idFrom][idTo] >= 1) {
                    // distance is already calculated - skip
                    continue;
                }

                int distanceInKm = GeoUtil.distanceInKm(
                        airportFrom.getCoord(),
                        airportTo.getCoord());

                // distance from - to
                distances[idFrom][idTo] = distanceInKm;

                // distance to - from (identical to the previous)
                distances[idTo][idFrom] = distanceInKm;
            }
        }

        this.airportDistances = distances;
    }

    /**
     * Generates Airport graph using maxDistanceInKm as the cutoff
     * for distance and a single airport as the source.
     * @param airportFrom The start airport
     * @param maxDistanceInKm Plane range in kilometers
     * @return AirportGraph
     */
    public AirportGraph generateAirportGraph(Airport airportFrom, int maxDistanceInKm) {
        int[][] distances = airportData.getAirportDistances();

        int fromId = airportFrom.getId();

        int count = airportData.getCount();

        boolean[] visited = new boolean[count];

        //noinspection unchecked
        ArrayList<AirportDistance>[] graph = new ArrayList[airportData.getCount()];

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // initial airport
        deque.add(fromId);

        while (true) {
            int curr;

            try {
                curr = deque.pop();
            } catch (NoSuchElementException e) {
                // empty deque
                break;
            }

            if (visited[curr]) {
                continue;
            }

            visited[curr] = true;

            ArrayList<AirportDistance> reachable = new ArrayList<>();

            Airport currAirport = airportMap.get(curr);

            int[] currDistances = distances[curr];

            for (int i = 0; i < count; i++) {
                if (i == curr) {
                    continue;
                }

                int currDistanceInKm = currDistances[i];

                if (currDistanceInKm > maxDistanceInKm) {
                    continue;
                }

                deque.add(i);

                Airport toAirport = airportMap.get(i);

                AirportDistance airportDistance = new AirportDistance(
                        currAirport,
                        toAirport,
                        currDistanceInKm);

                reachable.add(airportDistance);
            }

            // Collections.sort(reachable);

            graph[curr] = reachable;
        }

        return new AirportGraph(graph);
    }
}
