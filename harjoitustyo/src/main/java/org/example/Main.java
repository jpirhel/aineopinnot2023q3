package org.example;

import java.util.ArrayList;

import org.example.data.Airport;
import org.example.data.AirportData;
import org.example.data.AirportDataGenerator;
import org.example.data.AirportGraph;
import org.example.data.Importer;
import org.example.geo.GeoUtil;
import org.example.gui.MainWindow;
import org.example.logic.DijkstraSearch;

public class Main {
    private AirportData airportData;
    private AirportGraph airportGraph;
    private MainWindow mainWindow;

    private ArrayList<Airport> route;

    /**
     * Main entry point to the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main();

        // generate initial dataset
        main.generateData(417, 500);

        System.out.println("Calculating Dijkstra route...");

        main.testDijkstraSearch();

        main.initGui();
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
        rangeInKm = 250;
        // rangeInKm = 300;

        generateData(startAirportId, rangeInKm);
        route = dijkstraSearch(startAirport, destAirport, rangeInKm);

        printRoute(route);
    }

    private void printRoute(ArrayList<Airport> route) {
        System.out.println("Route:");

        if (route != null) {
            int totalDistance = 0;

            Airport destAirport = route.get(route.size() - 1);

            for (int i = 0; i < route.size() - 1; i++) {
                Airport curr = route.get(i);
                Airport next = route.get(i + 1);

                int distance = GeoUtil.distanceInKm(curr.getCoord(), next.getCoord());

                System.out.println("ICAO: " + curr.getIcao() + ", " + curr.getName() + ", " + curr.getCity() + ", " + curr.getCountry() + ", coordinates: " + curr.getCoord());
                System.out.println("-> distance to next hop: " + distance + " km");

                totalDistance += distance;
            }
            System.out.println("ICAO: " + destAirport.getIcao() + ", " + destAirport.getName() + ", " + destAirport.getCity() + ", " + destAirport.getCountry() + ", coordinates: " + destAirport.getCoord());
            System.out.println();
            System.out.println("total distance: " + totalDistance + " km");
        } else {
            System.out.println("No route found!");
        }
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
        if (route == null) {
            return;
        }

        MainWindow mainWindow = new MainWindow(airportData, route);
        mainWindow.show();
    }

    private ArrayList<Airport> dijkstraSearch(Airport startAirport, Airport destAirport, int rangeInKm) {
        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        //noinspection UnnecessaryLocalVariable
        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                startAirport.getId(),
                destAirport.getId(),
                rangeInKm,
                airportGraph);

        return normalizedPath;
    }
}
