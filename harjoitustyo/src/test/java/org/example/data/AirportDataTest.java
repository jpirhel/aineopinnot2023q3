package org.example.data;

//CHECKSTYLE.OFF: AvoidStarImport
import static org.junit.jupiter.api.Assertions.*;
//CHECKSTYLE.ON: AvoidStarImport

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AirportDataTest {
    @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
    private final int AIRPORT_DATASET_SIZE = 7698;

    private final AirportData airportData;

    public AirportDataTest() {
        RawAirportData rawAirportData = new RawAirportData();
        Importer importer = new Importer(rawAirportData);

        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        //noinspection UnnecessaryLocalVariable
        AirportData airportData = airportDataGenerator.getAirportData();

        this.airportData = airportData;
    }

    @Test
    void getCount() {
        int count = airportData.getCount();

        assertEquals(AIRPORT_DATASET_SIZE, count);
    }


    @Test
    void getAirports() {
        ArrayList<Airport> airports = airportData.getAirports();

        assertNotNull(airports);
        assertEquals(AIRPORT_DATASET_SIZE, airports.size());
    }

    @Test
    void getAirportDistances() {
        int[][] airportDistances = airportData.getAirportDistances();

        assertEquals(AIRPORT_DATASET_SIZE, airportDistances.length);

        int[] firstDistances = airportDistances[0];
        assertEquals(AIRPORT_DATASET_SIZE, firstDistances.length);
    }

    @Test
    void getAirportMap() {
        Map<Integer, Airport> airportMap = airportData.getAirportMap();

        assertNotNull(airportMap);
        assertEquals(AIRPORT_DATASET_SIZE, airportMap.size());
    }

    @Test
    void getIcaoIndex() {
        Map<String, Airport> icaoIndex = airportData.getIcaoIndex();

        assertNotNull(icaoIndex);
        assertEquals(AIRPORT_DATASET_SIZE, icaoIndex.size());
    }
}
