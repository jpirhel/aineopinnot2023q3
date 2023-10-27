package org.example.data;

import java.util.ArrayList;

/**
 * Imports airport data from a data file.
 */
public class Importer {
    private final RawAirportData rawAirportData;

    private ArrayList<Airport> airports;

    /**
     * @param rawAirportData Class to hold raw airport data.
     */
    public Importer(RawAirportData rawAirportData) {
        this.rawAirportData = rawAirportData;
    }

    /**
     * Imports airports from a raw data class
     *
     * @return List of Airport objects
     */
    public ArrayList<Airport> importAirports() {
        importDataToArray();

        return airports;
    }

    /**
     * Reads the raw airport data class and parses the contents into Airport objects.
     */
    private void importDataToArray() {
        ArrayList<String> lines;

        ArrayList<Airport> airports = new ArrayList<>();

        lines = rawAirportData.getRawData();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Airport airport = Airport.fromLine(i, line);
            airports.add(airport);
        }

        this.airports = airports;
    }
}
