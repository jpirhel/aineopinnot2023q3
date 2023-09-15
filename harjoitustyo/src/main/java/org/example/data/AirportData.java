package org.example.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holds various different types of data relating to airports.
 */
public class AirportData {
    private final ArrayList<Airport> airports;
    private final HashMap<Integer, Airport> airportMap;
    private final int[][] airportDistances;

    private final HashMap<String, Airport> icaoIndex;

    /**
     * @param airports
     * @param airportMap
     * @param airportDistances
     * @param icaoIndex
     */
    public AirportData(
            ArrayList<Airport> airports,
            HashMap<Integer, Airport> airportMap,
            int[][] airportDistances,
            HashMap<String, Airport> icaoIndex) {
        this.airports = airports;
        this.airportMap = airportMap;
        this.airportDistances = airportDistances;
        this.icaoIndex = icaoIndex;
    }

    /**
     * Number of airports in the data set
     * @return Count
     */
    public Integer getCount() {
        return airportMap.size();
    }

    /**
     * A list of airports
     * @return Airports
     */
    public ArrayList<Airport> getAirports() {
        return airports;
    }

    /**
     * Airport distance array
     * @return Distances
     */
    public int[][] getAirportDistances() {
        return airportDistances;
    }

    /**
     * Map from airport ID to Airport object
     * @return HashMap of airports
     */
    public HashMap<Integer, Airport> getAirportMap() {
        return airportMap;
    }

    /**
     * Airports indexed by ICAO code
     * @return Icao index
     */
    public HashMap<String, Airport> getIcaoIndex() {
        return icaoIndex;
    }
}
