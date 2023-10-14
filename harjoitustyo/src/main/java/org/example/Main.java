package org.example;

import java.util.ArrayList;

//CHECKSTYLE.OFF: AvoidStarImport
//CHECKSTYLE.ON: AvoidStarImport

import org.example.data.Airport;
import org.example.data.AirportData;
import org.example.data.AirportDataGenerator;
import org.example.data.AirportGraph;
import org.example.data.Importer;
import org.example.gui.MainWindow;
import org.example.logic.DijkstraSearch;

public class Main {
    private AirportData airportData;
    private AirportGraph airportGraph;
    private MainWindow mainWindow;

    /**
     * Main entry point to the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main();

        // generate initial dataset
        main.generateData(417,500);

        main.initGui();

        main.testDijkstraSearch();
    }

    private void testDijkstraSearch() {
        int startAirportId = 417; // Helsinki-Vantaa Airport

        // int destAirportId = 154; // Vancouver International Airport (Canada)
        int destAirportId = 2222; // Hamatsu Airport (Japan)

        // int destAirportId = 232; // Biskra Airport
        // int destAirportId = 339; // Berlin-Tempelhof International airport

        Airport startAirport = airportData.getAirports().get(startAirportId);
        Airport destAirport = airportData.getAirports().get(destAirportId);

        int rangeInKm;

        rangeInKm = 450;
        rangeInKm = 768;
        rangeInKm = 1000;
        rangeInKm = 2000;
        // rangeInKm = 300;


        generateData(startAirportId, rangeInKm);
        ArrayList<Airport> route = dijkstraSearch(startAirport, destAirport, rangeInKm);

        System.out.println("==========================================================");
        System.out.println("ROUTE: " + route);
        System.out.println("==========================================================");

        mainWindow.setMapRoute(route);
    }

    private void generateData(int fromAirportId, int rangeInKm) {
        String filename = "../data/airports.dat";

        Importer importer = new Importer(filename);
        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        AirportData airportData = airportDataGenerator.getAirportData();

        Airport airportFrom = airportData.getAirports().get(fromAirportId);

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                airportFrom,
                rangeInKm);

        this.airportData = airportData;
        this.airportGraph = airportGraph;
    }

    private void initGui() {
        MainWindow mainWindow = new MainWindow(airportData);
        mainWindow.show();

        this.mainWindow = mainWindow;
    }

    private ArrayList<Airport> dijkstraSearch(Airport startAirport, Airport destAirport, int rangeInKm) {
        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                startAirport.getId(),
                destAirport.getId(),
                rangeInKm,
                airportGraph);

        System.out.println("=============================================");
        System.out.println("normalizedPath:");

        if (normalizedPath != null) {
            for (Airport airport : normalizedPath) {
                System.out.println(airport);
            }
            System.out.println("normalizedPath size: " + normalizedPath.size());
        } else {
            System.out.println("NO PATH FOUND!");
        }

        System.out.println("=============================================");

        return normalizedPath;
    }
}
