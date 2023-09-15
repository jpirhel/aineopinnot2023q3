package org.example.data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * Imports airport data from a data file.
 */
public class Importer {
    private final String airportDataFilename;

    private ArrayList<Airport> airports;

    /**
     * @param filename
     */
    public Importer(String filename) {
        this.airportDataFilename = filename;
    }

    /**
     * Imports airports from a data file.
     *
     * @return List of Airport objects
     */
    public ArrayList<Airport> importAirports() {
        importFileToArray();

        return airports;
    }

    /**
     * Reads the data file (airports.dat) and parses the contents into Airport objects.
     */
    private void importFileToArray() {
        List<String> lines;

        ArrayList<Airport> airports = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(airportDataFilename), StandardCharsets.UTF_8);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                Airport airport = Airport.fromLine(i, line);
                airports.add(airport);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.airports = airports;
    }
}
