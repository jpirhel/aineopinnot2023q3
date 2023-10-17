package org.example.geo;

//CHECKSTYLE.OFF: AvoidStarImport
import static org.junit.jupiter.api.Assertions.*;
//CHECKSTYLE.ON: AvoidStarImport

import org.example.data.*;
import org.example.logic.DijkstraSearch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void routeTotalDistance() {
        Importer importer = new Importer(new RawAirportData());
        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        AirportData airportData = airportDataGenerator.getAirportData();

        // Helsinki-Vantaa Airport
        Airport startAirport = airportData.getIcaoIndex().get("EFHK");

        // Helsinki-Vantaa Airport
        Airport destAirport = airportData.getIcaoIndex().get("CYVR");

        int rangeInKm = 500;

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                startAirport,
                rangeInKm);

        DijkstraSearch dijkstraSearch = new DijkstraSearch(
                airportData.getAirports(),
                airportData.getAirportDistances());

        ArrayList<Airport> normalizedRoute = dijkstraSearch.normalizedSearch(
                startAirport.getId(),
                destAirport.getId(),
                airportGraph);

        System.out.println("normalizedRoute:" + normalizedRoute);

        int totalDistance = GeoUtil.routeTotalDistance(normalizedRoute, false);

        int correctTotalDistance = 359 + 493 + 345 + 308 + 244 + 418 + 301 + 246 + 478 + 479  + 336 + 353 + 326 + 344 + 233 + 455 + 496 + 488 + 334 + 345 + 221 + 402 + 348 + 432 + 374 + 235 + 334 + 338 + 369 + 408 + 423 + 470 + 428;

        assertEquals(correctTotalDistance, totalDistance);
    }
}
