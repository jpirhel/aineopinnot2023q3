package org.example;

//CHECKSTYLE.OFF: AvoidStarImport

import java.util.ArrayList;

import org.example.data.*;
import org.example.geo.GeoUtil;
import org.example.gui.MainWindow;
import org.example.logic.DijkstraSearch;

//CHECKSTYLE.ON: AvoidStarImport

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
        main.generateData(417, 501);

        // read command line parameters

        // the actual command to be performed by the program
        String command = "";

        // ICAO code of start airport
        String startIcao = "";

        // ICAO code of destination airport
        String destIcao = "";

        // the range of the plane used
        int rangeInKm = 0;

        String icaoSearch = "";

        try {
            command = args[0];
        } catch (ArrayIndexOutOfBoundsException ex) {
            exitUsage();
        }

        // show all airports on a map

        if (command.equals("airports")) {
            main.initGuiWithAirports();
        }

        // parse command line arguments for searches

        if (command.equals("dijkstra") || command.equals("idastar")) {
            try {
                startIcao = args[1];
                destIcao = args[2];
                rangeInKm = Integer.parseInt(args[3]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                exitUsage();
            }
        }

        // perform a dijkstra search

        if (command.equals("dijkstra")) {
            main.dijkstraSearchIcao(startIcao, destIcao, rangeInKm);
        }

        // perform a IDA* search

        if (command.equals("idastar")) {
            main.idastarSearchIcao(startIcao, destIcao, rangeInKm);
        }

        // show search results on a map

        if (command.equals("dijkstra") || command.equals("idastar")) {
            main.initGui();
        }

        // perform a string search of airports (name, city, country) to get the ICAO code

        if (command.equals("icao")) {
            try {
                icaoSearch = args[1];
            } catch (ArrayIndexOutOfBoundsException ex) {
                exitUsage();
            }

            main.icaoSearch(icaoSearch);
        }
    }

    /**
     * Performs a search of the airport database to find the ICAO code.
     *
     * @param icaoSearch The search string for finding the relevant airports.
     */
    private void icaoSearch(String icaoSearch) {
        if (icaoSearch == null) {
            exitUsage();
        }

        assert icaoSearch != null;

        if (icaoSearch.isEmpty()) {
            exitUsage();
        }

        RawAirportData rawAirportData = new RawAirportData();

        String search = icaoSearch.toLowerCase();

        int id = 0;

        ArrayList<Airport> found = new ArrayList<>();

        for (String line : rawAirportData.getRawData()) {
            id += 1;

            Airport airport = Airport.fromLine(id, line);

            if (airport.getName().toLowerCase().contains(search)
                    || airport.getCity().toLowerCase().contains(search)
                    || airport.getCountry().toLowerCase().contains(search)) {
                found.add(airport);
            }
        }

        for (Airport airport : found) {
            //CHECKSTYLE.OFF: LineLength
            System.out.println(airport.getIcao() + " " + airport.getName() + ", " + airport.getCity() + ", " + airport.getCountry() + " " + airport.getCoord());
            //CHECKSTYLE.ON: LineLength
        }

        if (found.isEmpty()) {
            System.out.println("No matches for search keyword '" + icaoSearch + "'.");
        } else {
            System.out.println("\n" + found.size() + " matches.");
        }
    }

    private static void exitUsage() {
        usage();
        System.exit(1);
    }

    private void idastarSearchIcao(String startIcao, String destIcao, int rangeInKm) {
        // FIXME to be implemented
    }

    /**
     * Prints program usage to standard output.
     */
    private static void usage() {
        //CHECKSTYLE.OFF: LineLength

        System.out.println("Usage:");
        System.out.println("./gradlew run --args='icao <SEARCH_STRING>'");
        System.out.println("./gradlew run --args='dijkstra <START_AIRPORT_ICAO> <END_AIRPORT_ICAO> <RANGE_IN_KM>'");
        System.out.println("./gradlew run --args='idastar <START_AIRPORT_ICAO> <END_AIRPORT_ICAO> <RANGE_IN_KM>'");
        System.out.println("./gradlew run --args='airports'");

        //CHECKSTYLE.ON: LineLength
    }

    /**
     * Performs a search of the shortest route between airports using Dijkstra's algorithm.
     *
     * @param startIcao ICAO code of start airport
     * @param destIcao  ICAO code of destination airport
     * @param rangeInKm Range of plane (in kilometers)
     */
    private void dijkstraSearchIcao(String startIcao, String destIcao, int rangeInKm) {
        int startAirportId = airportData.getIcaoIndex().get(startIcao).getId();
        int destAirportId = airportData.getIcaoIndex().get(destIcao).getId();

        Airport startAirport = airportData.getAirports().get(startAirportId);
        Airport destAirport = airportData.getAirports().get(destAirportId);

        generateData(startAirportId, rangeInKm);

        route = dijkstraSearch(startAirport, destAirport);

        printRoute(route);
    }

    /**
     * Prints the calculated route to standard output.
     *
     * @param route The calculated route
     */
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

    /**
     * Generates airport dataset & graph used by the program.
     *
     * @param fromAirportId The start airport ID.
     * @param rangeInKm     The range of the plane in kilometers.
     */
    private void generateData(int fromAirportId, int rangeInKm) {
        Importer importer = new Importer(new RawAirportData());
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

    /**
     * Initializes the program GUI and shows all airports in the data set.
     */
    private void initGuiWithAirports() {
        MainWindow mainWindow = new MainWindow(airportData, route, true);
        mainWindow.show();
    }

    /**
     * Initializes the program GUI by creating the main window.
     */
    private void initGui() {
        if (route == null) {
            return;
        }

        MainWindow mainWindow = new MainWindow(airportData, route, false);
        mainWindow.show();
    }

    /**
     * Performs the actual Dijkstra's algoritm search for the shortest route between airports.
     *
     * @param startAirport The start Airport object
     * @param destAirport  The destination Airport object
     * @return ArrayList of airports from the start to the destination
     */
    private ArrayList<Airport> dijkstraSearch(
            Airport startAirport,
            Airport destAirport) {
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
