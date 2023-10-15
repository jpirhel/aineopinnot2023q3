package org.example.logic;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.example.data.*;

import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class DijkstraSearchTest {
    private final AirportDataGenerator airportDataGenerator;
    private final AirportData airportData;

    private final Airport airportFrom;
    private final Airport airportTo;

    public DijkstraSearchTest() {
        RawAirportData rawAirportData = new RawAirportData();
        Importer importer = new Importer(rawAirportData);
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

    private AirportGraph generateAirportGraph(int testRangeInKm) {
        //noinspection UnnecessaryLocalVariable
        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                airportFrom,
                testRangeInKm);

        return airportGraph;
    }

    private ArrayList<Airport> normalizedSearch(int rangeInKm) {
        AirportGraph airportGraph = generateAirportGraph(rangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        //noinspection UnnecessaryLocalVariable
        ArrayList<Airport> normalizedPath = dijkstraSearch.normalizedSearch(
                airportFrom.getId(),
                airportTo.getId(),
                airportGraph);

        return normalizedPath;
    }

    @Test
    void normalizedSearch400() {
        int testRangeInKm = 400;

        ArrayList<Airport> normalizedPath = normalizedSearch(testRangeInKm);

        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearch450() {
        int testRangeInKm = 450;

        ArrayList<Airport> normalizedPath = normalizedSearch(testRangeInKm);

        assertEquals(39, normalizedPath.size());
    }

    @Test
    void normalizedSearch500() {
        int testRangeInKm = 500;

        ArrayList<Airport> normalizedPath = normalizedSearch(testRangeInKm);

        assertEquals(34, normalizedPath.size());
    }

    @Test
    void normalizedSearch1000() {
        int testRangeInKm = 1000;

        ArrayList<Airport> normalizedPath = normalizedSearch(testRangeInKm);

        assertEquals(13, normalizedPath.size());
    }

    @Test
    void normalizedSearch2000() {
        int testRangeInKm = 2000;

        ArrayList<Airport> normalizedPath = normalizedSearch(testRangeInKm);

        assertEquals(7, normalizedPath.size());
    }
}
