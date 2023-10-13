package org.example.logic;

//CHECKSTYLE.OFF: AvoidStarImport
import org.example.data.*;
//CHECKSTYLE.ON: AvoidStarImport

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraSearchTest {
    private final AirportDataGenerator airportDataGenerator;
    private final AirportData airportData;

    private final Airport airportFrom;
    private final Airport airportTo;

    public DijkstraSearchTest() {
        String filename = "../data/airports.dat";

        Importer importer = new Importer(filename);
        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);
        this.airportDataGenerator = airportDataGenerator;

        AirportData airportData = airportDataGenerator.getAirportData();

        this.airportData = airportData;

        int startAirportId = 417; // Helsinki-Vantaa
        int destAirportId = 154; // Vancouver International

        this.airportFrom = airportData.getAirports().get(startAirportId);
        this.airportTo = airportData.getAirports().get(destAirportId);
    }

    @BeforeEach
    void setUp() {

    }

    private AirportGraph generateAirportGraph(int testRangeInKm) {
        //noinspection UnnecessaryLocalVariable
        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(airportFrom, testRangeInKm);

        return airportGraph;
    }

    @Test
    void normalizedSearch400() {
        int testRangeInKm = 400;

        AirportGraph airportGraph = generateAirportGraph(testRangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                airportFrom.getId(),
                airportTo.getId(),
                testRangeInKm,
                airportGraph);

        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearch450() {
        int testRangeInKm = 450;

        AirportGraph airportGraph = generateAirportGraph(testRangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                airportFrom.getId(),
                airportTo.getId(),
                testRangeInKm,
                airportGraph);

        assertEquals(39, normalizedPath.size());
    }

    @Test
    void normalizedSearch500() {
        int testRangeInKm = 500;

        AirportGraph airportGraph = generateAirportGraph(testRangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                airportFrom.getId(),
                airportTo.getId(),
                testRangeInKm,
                airportGraph);

        assertEquals(34, normalizedPath.size());
    }

    @Test
    void normalizedSearch1000() {
        int testRangeInKm = 1000;

        AirportGraph airportGraph = generateAirportGraph(testRangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                airportFrom.getId(),
                airportTo.getId(),
                testRangeInKm,
                airportGraph);

        assertEquals(13, normalizedPath.size());
    }

    @Test
    void normalizedSearch2000() {
        int testRangeInKm = 2000;

        AirportGraph airportGraph = generateAirportGraph(testRangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                airportFrom.getId(),
                airportTo.getId(),
                testRangeInKm,
                airportGraph);

        assertEquals(7, normalizedPath.size());
    }
}
