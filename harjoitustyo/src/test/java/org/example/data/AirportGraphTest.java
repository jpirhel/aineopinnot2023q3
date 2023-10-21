package org.example.data;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class AirportGraphTest {
    private final AirportDataGenerator airportDataGenerator;

    public AirportGraphTest() {
        RawAirportData rawAirportData = new RawAirportDataWorld();
        Importer importer = new Importer(rawAirportData);
        ArrayList<Airport> airports = importer.importAirports();

        //noinspection UnnecessaryLocalVariable
        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        this.airportDataGenerator = airportDataGenerator;
    }

    @Test
    void getGraph() {
        Airport testAirport = airportDataGenerator
                .getAirportData()
                .getAirportMap()
                .get(417); // Helsinki-Vantaa

        AirportGraph graph =  airportDataGenerator.generateAirportGraph(
                testAirport,
                200);

        assertNotNull(graph);

        // TODO more tests to ensure correctness of the graph
    }
}
