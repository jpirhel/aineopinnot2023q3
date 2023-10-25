package org.example;

//CHECKSTYLE.OFF: AvoidStarImport

import java.util.ArrayList;
import java.util.Arrays;

import org.example.data.*;
import org.example.geo.GeoUtil;
import org.example.gui.MainWindow;
import org.example.logic.DijkstraSearch;
import org.example.logic.IdastarSearch;

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
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            usage();
            return;
        }

        // check that the command is valid

        ArrayList<String> validCommands = new ArrayList<>(
                Arrays.asList(
                        "icao",
                        "airports",
                        "dijkstra",
                        "idastar",
                        "planes",
                        "airports"));

        if (!validCommands.contains(command)) {
            usage();
            return;
        }

        // check data set - used by Dijkstra and IDA*

        String dataSet = "world";

        try {
            dataSet = args[4];
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            // nothing - dataSet argument is optional
        }

        ArrayList<String> validDataSets = new ArrayList<>(Arrays.asList("world", "finland"));

        if (!validDataSets.contains(dataSet)) {
            dataSet = "world"; // default to world data set
        }

        // generate initial dataset for commands that need it
        // this is unfortunately redundant
        if (command.equals("dijkstra")
                || command.equals("idastar")
                || command.equals("icao")
                || command.equals("airports")) {
            if (dataSet.equals("finland")) {
                // start at first airport in the data set
                main.generateData(1, 501, "finland");
            } else {
                // start at Helsinki-Vantaa
                main.generateData(417, 501, "world");
            }
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

                // perform a dijkstra search

                if (command.equals("dijkstra")) {
                    main.dijkstraSearchIcao(startIcao, destIcao, rangeInKm, dataSet);
                }

                // perform a IDA* search

                if (command.equals("idastar")) {
                    main.idastarSearchIcao(startIcao, destIcao, rangeInKm, dataSet);
                }

                // show search results on a map

                main.initGui();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                usage();
                return;
            }
        }

        // perform a string search of airports (name, city, country) to get the ICAO code

        if (command.equals("icao")) {
            try {
                icaoSearch = args[1];

                String icaoDataSet = "world";

                try {
                    icaoDataSet = args[2];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // nothing
                }

                if (!validDataSets.contains(icaoDataSet)) {
                    icaoDataSet = "world"; // default to world data set
                }

                main.icaoSearch(icaoSearch, icaoDataSet);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
                usage();
                return;
            }
        }

        // print plane ranges

        if (command.equals("planes")) {
            main.planeData();
        }
    }

    private void planeData() {
        System.out.println("Some aeroplane ranges:");
        System.out.println();
        System.out.println("768   km - Cessna 152 (small plane)");
        System.out.println("1639  km - Cessna 172 Turbo Skyhawk JT-A (small plane)");
        System.out.println("6500  km - Cessna Citation Longitude (business jet)");
        System.out.println("14800 km - Airbus A380 (large body commercial jet)");
    }

    /**
     * Performs a search of the airport database to find the ICAO code.
     *
     * @param icaoSearch The search string for finding the relevant airports.
     */
    private void icaoSearch(String icaoSearch, String dataSet) {
        RawAirportData rawAirportData;

        if (dataSet.equals("finland")) {
            rawAirportData = new RawAirportDataFinland();
        } else {
            rawAirportData = new RawAirportDataWorld();
        }

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

    /**
     * Prints program usage to standard output.
     */
    private static void usage() {
        //CHECKSTYLE.OFF: LineLength

        System.out.println("Usage:");
        System.out.println();

        System.out.println("./gradlew run --args='planes'");
        System.out.println("*** display information about aeroplane ranges");
        System.out.println();

        System.out.println("./gradlew run --args='icao <SEARCH_STRING>' - search for airports");
        System.out.println("*** search for airport ICAO codes (matches airport name, city, country");
        System.out.println();

        System.out.println("./gradlew run --args='dijkstra <START_AIRPORT_ICAO> <END_AIRPORT_ICAO> <RANGE_IN_KM> (<DATASET>)'");
        System.out.println("*** find shortest path using Dijkstra's algorithm. Parameter <DATASET> is optional, and can be set to 'finland'.");
        System.out.println();

        System.out.println("./gradlew run --args='idastar <START_AIRPORT_ICAO> <END_AIRPORT_ICAO> <RANGE_IN_KM> (<DATASET>)'");
        System.out.println("*** find shortest path using IDA* algorithm. Parameter <DATASET> is optional, and can be set to 'finland'.");
        System.out.println();

        System.out.println("./gradlew run --args='airports'");
        System.out.println("*** display all airports on a map");

        //CHECKSTYLE.ON: LineLength
    }

    /**
     * Performs a search of the shortest route between airports using Dijkstra's algorithm.
     *
     * @param startIcao ICAO code of start airport
     * @param destIcao  ICAO code of destination airport
     * @param rangeInKm Range of plane (in kilometers)
     */
    private void dijkstraSearchIcao(
            String startIcao,
            String destIcao,
            int rangeInKm,
            String dataSet) {
        int startAirportId = getAirportIdFromIcao(startIcao);
        int destAirportId = getAirportIdFromIcao(destIcao);

        Airport startAirport = airportData.getAirports().get(startAirportId);
        Airport destAirport = airportData.getAirports().get(destAirportId);

        generateData(startAirportId, rangeInKm, dataSet);


        route = dijkstraSearch(startAirport, destAirport);

        printRoute(route, dataSet);
    }

    /**
     * Search for an airport matching the given ICAO code.
     *
     * @param icao The Icao Code
     * @return The airport object, if found.
     */
    private int getAirportIdFromIcao(String icao) {
        int airportId = 0;

        try {
            airportId = airportData.getIcaoIndex().get(icao).getId();
        } catch (NullPointerException ex) {
            System.out.println("Check start and destination airport ICAO codes.");
            System.exit(0);
        }

        return airportId;
    }

    /**
     * Performs a search of the shortest route between airports using IDA* algorithm.
     *
     * @param startIcao ICAO code of start airport
     * @param destIcao  ICAO code of destination airport
     * @param rangeInKm Range of plane (in kilometers)
     */
    private void idastarSearchIcao(
            String startIcao,
            String destIcao,
            int rangeInKm,
            String dataSet) {
        int startAirportId = getAirportIdFromIcao(startIcao);
        int destAirportId = getAirportIdFromIcao(destIcao);

        Airport startAirport = airportData.getAirports().get(startAirportId);
        Airport destAirport = airportData.getAirports().get(destAirportId);

        generateData(startAirportId, rangeInKm, dataSet);

        route = idastarSearch(startAirport, destAirport);

        printRoute(route, dataSet);
    }

    /**
     * Prints the calculated route to standard output.
     *
     * @param route   The calculated route
     * @param dataSet The data set used
     */
    private void printRoute(ArrayList<Airport> route, String dataSet) {
        System.out.println("Data set: " + dataSet + ", Route:");

        if (route != null && route.size() > 1) {
            GeoUtil.routeTotalDistance(route, true);
        } else {
            System.out.println("No route found!");
        }
    }

    /**
     * Generates airport dataset & graph used by the program.
     *
     * @param fromAirportId The start airport ID.
     * @param rangeInKm     The range of the plane in kilometers.
     * @param dataSet       The airport dataset used - "finland" or "world"
     */
    private void generateData(int fromAirportId, int rangeInKm, String dataSet) {
        RawAirportData rawAirportData;

        // use Finland dataset
        if (dataSet.equals("finland")) {
            rawAirportData = new RawAirportDataFinland();
        } else {
            rawAirportData = new RawAirportDataWorld();
        }

        Importer importer = new Importer(rawAirportData);
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
        if (route == null || route.size() < 2) {
            return;
        }

        MainWindow mainWindow = new MainWindow(airportData, route, false);
        mainWindow.show();
    }

    /**
     * Performs the actual Dijkstra's algorithm search for the shortest route between airports.
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

    /**
     * Perform a IDA* search of routes between airports.
     *
     * @param startAirport The Start airport
     * @param destAirport  The Destination airport
     * @return List of the airports on the found route
     */
    private ArrayList<Airport> idastarSearch(
            Airport startAirport,
            Airport destAirport) {
        IdastarSearch idastarSearch = new IdastarSearch(airportData);

        //noinspection UnnecessaryLocalVariable
        ArrayList<Airport> normalizedPath = idastarSearch.normalizedSearch(
                startAirport,
                destAirport,
                airportGraph);

        return normalizedPath;
    }
}
