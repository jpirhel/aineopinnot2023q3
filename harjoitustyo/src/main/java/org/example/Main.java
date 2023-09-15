package org.example;

import java.util.ArrayList;

import org.example.data.Airport;
import org.example.data.AirportData;
import org.example.data.AirportDataGenerator;
import org.example.data.AirportGraph;
import org.example.data.Importer;

public class Main {
    /**
     * Main entry point to the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        String filename = "../data/airports.dat";

        Importer importer = new Importer(filename);
        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        int helsinkiId = 417; // 421?

        int testRange;

        // testRange = 60; // reachableCount: 6
        // testRange = 100; // reachableCount: 60
        // testRange = 150; // reachableCount: 1727
        // testRange = 200; // reachableCount: 1880
        // testRange = 300; // reachableCount: 3626
        // testRange = 400; // reachableCount: 3912
        // testRange = 500; // reachableCount: 7433
        // testRange = 768; // reachableCount: 7521
        // testRange = 1500; // reachableCount: 7668
        testRange = 18000; // reachableCount: 7679

        // int cessna152Range = 768; // reachableCount: 7521
        // int airbusA350XWBUltraLongRangeDistance = 18000; // reachableCount: 7697

        AirportData airportData = airportDataGenerator.getAirportData();
        Airport airportFrom = airportData.getAirportMap().get(helsinkiId);

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                airportFrom,
                testRange);
    }
}
