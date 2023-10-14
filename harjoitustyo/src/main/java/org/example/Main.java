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
    private ArrayList<Airport> route;

    /**
     * Main entry point to the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Main main = new Main();

        // generate initial dataset - this is unfortunately redundant
        main.generateData(417, 500);

        String command = "";
        String startIcao = "";
        String destIcao = "";
        int rangeInKm = 0;

        try {
            command = args[0];
        } catch (ArrayIndexOutOfBoundsException ex) {
            usage();
            System.exit(1);
        }

        if (command.equals("dijkstra")) {
            try {
                startIcao = args[1];
                destIcao = args[2];
                rangeInKm = Integer.parseInt(args[3]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                usage();
                System.exit(1);
            }

            main.dijkstraSearchIcao(startIcao, destIcao, rangeInKm);
            main.initGui();
        }

        if (command.equals("airports")) {
            main.initGuiWithAirports();
        }


    }

    private static void usage() {
        //CHECKSTYLE.OFF: LineLength

        System.out.println("Usage:");
        System.out.println("./gradlew run --args='dijkstra <START_AIRPORT_ICAO> <END_AIRPORT_ICAO> <RANGE_IN_KM>'");
        System.out.println("./gradlew run --args='idastar <START_AIRPORT_ICAO> <END_AIRPORT_ICAO> <RANGE_IN_KM>'");
        System.out.println("./gradlew run --args='airports'");

        //CHECKSTYLE.ON: LineLength
    }

    private void dijkstraSearchIcao(String startIcao, String destIcao, int rangeInKm) {
        int startAirportId = airportData.getIcaoIndex().get(startIcao).getId();
        int destAirportId = airportData.getIcaoIndex().get(destIcao).getId();

        Airport startAirport = airportData.getAirports().get(startAirportId);
        Airport destAirport = airportData.getAirports().get(destAirportId);

        generateData(startAirportId, rangeInKm);
        route = dijkstraSearch(startAirport, destAirport, rangeInKm);

        printRoute(route);
    }

    private void printRoute(ArrayList<Airport> route) {
        System.out.println("Route:");

        //CHECKSTYLE.OFF: LineLength

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
            System.out.println("total distance: " + totalDistance + " km" + ", number of hops: " + route.size());
        } else {
            System.out.println("No route found!");
        }

        //CHECKSTYLE.ON: LineLength
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

    private void initGuiWithAirports() {
        MainWindow mainWindow = new MainWindow(airportData, route, true);
        mainWindow.show();
    }

    private void initGui() {
        if (route == null) {
            return;
        }

        MainWindow mainWindow = new MainWindow(airportData, route, false);
        mainWindow.show();
    }

    private ArrayList<Airport> dijkstraSearch(
            Airport startAirport,
            Airport destAirport,
            int rangeInKm) {
        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        //noinspection UnnecessaryLocalVariable
        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                startAirport.getId(),
                destAirport.getId(),
                airportGraph);

        return normalizedPath;
    }
}
