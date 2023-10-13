package org.example;

import java.util.ArrayList;

import javax.swing.*;

import org.example.data.Airport;
import org.example.data.AirportData;
import org.example.data.AirportDataGenerator;
import org.example.data.AirportGraph;
import org.example.data.Importer;
import org.example.gui.MainWindow;
import org.example.logic.DijkstraSearch;

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

        int testRangeInKm;

        // testRangeInKm = 60; // reachableCount: 6
        // testRangeInKm = 100; // reachableCount: 60
        // testRangeInKm = 150; // reachableCount: 1727
        // testRangeInKm = 200; // reachableCount: 1880
        // testRangeInKm = 300; // reachableCount: 3626
        // testRangeInKm = 400; // reachableCount: 3912
        // testRangeInKm = 500; // reachableCount: 7433
        // testRangeInKm = 768; // reachableCount: 7521
        // testRangeInKm = 1500; // reachableCount: 7668
        // testRangeInKm = 18000; // reachableCount: 7679

        testRangeInKm = 5000; // kilometers

        // int cessna152Range = 768; // reachableCount: 7521
        // int airbusA350XWBUltraLongRangeDistance = 18000; // reachableCount: 7697

        AirportData airportData = airportDataGenerator.getAirportData();
        Airport airportFrom = airportData.getAirportMap().get(helsinkiId);

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                airportFrom,
                testRangeInKm);

//        Main main = new Main();
//        main.initGui();

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        int startAirportId = 417; // Helsinki-Vantaa Airport
        // int destAirportId = 232; // Biskra Airport
        // int destAirportId = 339; // Berlin-Tempelhof International airport
        int destAirportId = 154; // Vancouver International Airport

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                startAirportId,
                destAirportId,
                testRangeInKm,
                airportGraph);

        System.out.println("=============================================");
        System.out.println("normalizedPath:");

        if (normalizedPath != null) {
            for (Airport airport : normalizedPath) {
                System.out.println(airport);
            }
        } else {
            System.out.println("NO PATH FOUND!");
        }

        System.out.println("=============================================");
    }

    private void initGui() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.show();
    }
}
