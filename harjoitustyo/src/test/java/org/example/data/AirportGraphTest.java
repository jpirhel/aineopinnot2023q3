package org.example.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AirportGraphTest {
    private final AirportDataGenerator airportDataGenerator;

    public AirportGraphTest() {
        String filename = "../data/airports.dat";

        Importer importer = new Importer(filename);
        ArrayList<Airport> airports = importer.importAirports();

        //noinspection UnnecessaryLocalVariable
        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        this.airportDataGenerator = airportDataGenerator;
    }

    @Test
    void getGraph() {
        Airport testAirport = airportDataGenerator.getAirportData().getAirportMap().get(417); // Helsinki-Vantaa

        AirportGraph graph =  airportDataGenerator.generateAirportGraph(testAirport, 200);

        assertNotNull(graph);

        // TODO more tests to ensure correctness of the graph


    }
}
