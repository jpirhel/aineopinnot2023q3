package org.example.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;

class AirportDataGeneratorTest {
    private final AirportDataGenerator airportDataGenerator;

    public AirportDataGeneratorTest() {
        String filename = "../data/airports.dat";

        Importer importer = new Importer(filename);
        ArrayList<Airport> airports = importer.importAirports();

        //noinspection UnnecessaryLocalVariable
        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        this.airportDataGenerator = airportDataGenerator;
    }


    @Test
    void getAirportData() {
        AirportData airportData = airportDataGenerator.getAirportData();

        assertNotNull(airportData);

        // TODO add more tests to ensure correctness of airport data
    }

    @Test
    void generateAirportGraph() {
        int maxDistanceInKm = 200;

        Airport testAirport = airportDataGenerator.getAirportData().getAirportMap().get(417); // Helsinki-Vantaa

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(testAirport, maxDistanceInKm);

        assertNotNull(airportGraph);

        ArrayList<AirportDistance>[] testGraph = airportGraph.getGraph();

        assertNotNull(testGraph);

        // TODO add more tests to ensure correctness of the graph
    }
}
