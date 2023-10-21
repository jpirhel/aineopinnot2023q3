package org.example.geo;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.example.data.*;
import org.example.logic.DijkstraSearch;

import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class GeoUtilTest {
    @Test
    void creation() {
        GeoUtilTest geoUtilTest = new GeoUtilTest();
        assertInstanceOf(GeoUtilTest.class, geoUtilTest);
    }

    @Test
    void distanceInKm() {
        // data from gcmap.com
        Coord helsinkiAirport = new Coord(60.317222, 24.963333);
        Coord tampereAirport = new Coord(61.415278, 23.587778);

        // Data source: greatcirclemapper.net
        int correctDistanceInKm = 143;

        int distance = GeoUtil.distanceInKm(helsinkiAirport, tampereAirport);

        assertEquals(correctDistanceInKm, distance);
    }

    AirportDataGenerator airportDataGenerator(RawAirportData rawAirportData) {
        Importer importer = new Importer(rawAirportData);

        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        return airportDataGenerator;
    }

    @Test
    void routeTotalDistanceWorld() {
        // generate airport graph
        AirportDataGenerator airportDataGenerator = airportDataGenerator(new RawAirportDataWorld());

        AirportData airportData = airportDataGenerator.getAirportData();

        // Helsinki-Vantaa Airport
        Airport startAirport = airportData.getIcaoIndex().get("EFHK");

        // Vancouver International Airport
        Airport destAirport = airportData.getIcaoIndex().get("CYVR");

        int rangeInKm = 500;

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                startAirport,
                rangeInKm);

        // search for the shortest path

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedRoute = dijkstraSearch.normalizedSearch(
                startAirport.getId(),
                destAirport.getId(),
                airportGraph);

        // calculate total distance

        int totalDistance = GeoUtil.routeTotalDistance(normalizedRoute, false);

        // correct distance is the sum of the hop distances

        //CHECKSTYLE.OFF: LineLength
        int correctTotalDistance = 359 + 493 + 345 + 308 + 244 + 418 + 301 + 246 + 478 + 479 + 336 + 353 + 326 + 344 + 233 + 455 + 496 + 488 + 334 + 345 + 221 + 402 + 348 + 432 + 374 + 235 + 334 + 338 + 369 + 408 + 423 + 470 + 428;
        //CHECKSTYLE.ON: LineLength

        assertEquals(correctTotalDistance, totalDistance);

        // test null route distance

        ArrayList<Airport> nullRoute = null;

        //noinspection ConstantValue
        int nullRouteDistance = GeoUtil.routeTotalDistance(nullRoute, false);

        assertEquals(nullRouteDistance, 0);

        // check printed output

        // redirect standard output for capture

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        //noinspection unused
        int unused = GeoUtil.routeTotalDistance(normalizedRoute, true);

        String capturedOutput = out.toString();

        String correctInfoString = "total distance: 12163 km, number of hops: 34";

        Boolean hasMatch = capturedOutput.contains(correctInfoString);

        assertEquals(true, hasMatch);

        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out2));

        //noinspection unused,ConstantValue
        int unused2 = GeoUtil.routeTotalDistance(nullRoute, true);

        String capturedOutput2 = out2.toString();

        String correctNullInfoString = "total distance: 0 km, number of hops: 0";

        Boolean hasMatchNull = capturedOutput2.contains(correctNullInfoString);

        assertEquals(true, hasMatchNull);
    }

    @Test
    void routeTotalDistanceFinland() {
        // generate airport graph
        AirportDataGenerator airportDataGenerator = airportDataGenerator(new RawAirportDataFinland());

        AirportData airportData = airportDataGenerator.getAirportData();

        // Helsinki-Vantaa Airport
        Airport startAirport = airportData.getIcaoIndex().get("EFHK");

        // Rovaniemi airport
        Airport destAirport = airportData.getIcaoIndex().get("EFRO");

        int rangeInKm = 500;

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                startAirport,
                rangeInKm);

        // search for the shortest path

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedRoute = dijkstraSearch.normalizedSearch(
                startAirport.getId(),
                destAirport.getId(),
                airportGraph);

        // calculate total distance

        int totalDistance = GeoUtil.routeTotalDistance(normalizedRoute, false);

        // correct distance is the sum of the hop distances

        int correctTotalDistance = 38 + 476 + 183;

        assertEquals(correctTotalDistance, totalDistance);

        // test null route distance

        ArrayList<Airport> nullRoute = null;

        //noinspection ConstantValue
        int nullRouteDistance = GeoUtil.routeTotalDistance(nullRoute, false);

        assertEquals(nullRouteDistance, 0);

        // check printed output

        // redirect standard output for capture

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        //noinspection unused
        int unused = GeoUtil.routeTotalDistance(normalizedRoute, true);

        String capturedOutput = out.toString();

        String correctInfoString = "total distance: 697 km, number of hops: 4";

        Boolean hasMatch = capturedOutput.contains(correctInfoString);

        assertEquals(true, hasMatch);

        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out2));

        //noinspection unused,ConstantValue
        int unused2 = GeoUtil.routeTotalDistance(nullRoute, true);

        String capturedOutput2 = out2.toString();

        String correctNullInfoString = "total distance: 0 km, number of hops: 0";

        Boolean hasMatchNull = capturedOutput2.contains(correctNullInfoString);

        assertEquals(true, hasMatchNull);
    }
}
