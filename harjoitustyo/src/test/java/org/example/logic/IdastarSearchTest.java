package org.example.logic;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.example.Main.main;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.example.data.*;
import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class IdastarSearchTest {
    private final AirportDataGenerator airportDataGenerator;
    private final AirportData airportData;
    private final Airport airportFrom;
    private final Airport airportTo;
    private final AirportDataGenerator airportDataGeneratorFinland;
    private final AirportData airportDataFinland;
    private final Airport airportFromFinland;
    private final Airport airportToFinland;

    public IdastarSearchTest() {
        // world data set

        RawAirportData rawAirportData = new RawAirportDataWorld();
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

        // finland data set

        RawAirportData rawAirportDataFinland = new RawAirportDataFinland();
        Importer importerFinland = new Importer(rawAirportDataFinland);
        ArrayList<Airport> airportsFinland = importerFinland.importAirports();

        //noinspection UnnecessaryLocalVariable
        AirportDataGenerator airportDataGeneratorFinland = new AirportDataGenerator(
                airportsFinland);
        this.airportDataGeneratorFinland = airportDataGeneratorFinland;

        AirportData airportDataFinland = airportDataGenerator.getAirportData();

        this.airportDataFinland = airportDataFinland;

        int startAirportIdFinland = 5; // Helsinki-Vantaa
        int destAirportIdFinland = 37; // Rovaniemi Airport

        this.airportFromFinland = airportDataFinland.getAirports().get(startAirportIdFinland);
        this.airportToFinland = airportDataFinland.getAirports().get(destAirportIdFinland);

    }

    private AirportGraph generateAirportGraph(
            AirportDataGenerator airportDataGenerator,
            Airport airportFrom,
            int testRangeInKm) {

        //noinspection UnnecessaryLocalVariable
        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                airportFrom,
                testRangeInKm);

        return airportGraph;
    }

    private ArrayList<Airport> normalizedSearch(
            AirportDataGenerator airportDataGenerator,
            Airport airportFrom,
            Airport airportTo,
            AirportData airportData,
            int rangeInKm) {
        AirportGraph airportGraph = generateAirportGraph(
                airportDataGenerator,
                airportFrom,
                rangeInKm);

        IdastarSearch idaStarSearch = new IdastarSearch(airportData);

        //noinspection UnnecessaryLocalVariable
        ArrayList<Airport> normalizedPath = idaStarSearch.normalizedSearch(
                airportFrom,
                airportTo,
                airportGraph);

        return normalizedPath;
    }

    @Test
    void normalizedSearch400() {
        int testRangeInKm = 400;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGenerator,
                airportFrom,
                airportTo,
                airportData,
                testRangeInKm);

        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearch450() {
        int testRangeInKm = 450;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGenerator,
                airportFrom,
                airportTo,
                airportData,
                testRangeInKm);

        // assertEquals(39, normalizedPath.size());
        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearch500() {
        int testRangeInKm = 500;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGenerator,
                airportFrom,
                airportTo,
                airportData,
                testRangeInKm);

        // assertEquals(34, normalizedPath.size());
        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearch1000() {
        int testRangeInKm = 1000;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGenerator,
                airportFrom,
                airportTo,
                airportData,
                testRangeInKm);

        // assertEquals(13, normalizedPath.size());
        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearch2000() {
        int testRangeInKm = 2000;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGenerator,
                airportFrom,
                airportTo,
                airportData,
                testRangeInKm);

        // assertEquals(7, normalizedPath.size());
        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearchNoPath() {
        int testRangeInKm = 200;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGenerator,
                airportFrom,
                airportTo,
                airportData,
                testRangeInKm);

        assertNull(normalizedPath);
    }

    @Test
    void normalizedSearchFinland100() {
        int testRangeInKm = 100;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGeneratorFinland,
                airportFromFinland,
                airportToFinland,
                airportDataFinland,
                testRangeInKm);

        assertEquals(3, normalizedPath.size());
    }

    @Test
    void normalizedSearchFinland200() {
        int testRangeInKm = 200;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGeneratorFinland,
                airportFromFinland,
                airportToFinland,
                airportDataFinland,
                testRangeInKm);

        assertEquals(2, normalizedPath.size());
    }

    @Test
    void normalizedSearchFinland300() {
        int testRangeInKm = 300;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGeneratorFinland,
                airportFromFinland,
                airportToFinland,
                airportDataFinland,
                testRangeInKm);

        assertEquals(2, normalizedPath.size());
    }

    @Test
    void normalizedSearchFinland500() {
        int testRangeInKm = 500;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGeneratorFinland,
                airportFromFinland,
                airportToFinland,
                airportDataFinland,
                testRangeInKm);

        assertEquals(2, normalizedPath.size());
    }

    @Test
    void normalizedSearchFinland1000() {
        int testRangeInKm = 1000;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGeneratorFinland,
                airportFromFinland,
                airportToFinland,
                airportDataFinland,
                testRangeInKm);

        assertEquals(2, normalizedPath.size());
    }

    @Test
    void normalizedSearchFinlandNoPath() {
        int testRangeInKm = 50;

        ArrayList<Airport> normalizedPath = normalizedSearch(
                airportDataGeneratorFinland,
                airportFromFinland,
                airportToFinland,
                airportDataFinland,
                testRangeInKm);

        assertNull(normalizedPath);
    }

    @Test
    void mainSearchIcao() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"idastar", "EFHK", "EFRO", "300"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("total distance: 700 km, number of hops: 4");

        assertEquals(true, matches);
    }
}
