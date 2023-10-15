package org.example.data;

//CHECKSTYLE.OFF: AvoidStarImport
import static org.junit.jupiter.api.Assertions.*;
//CHECKSTYLE.ON: AvoidStarImport

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ImporterTest {
    private final Importer importer;

    public ImporterTest() {
        RawAirportData rawAirportData = new RawAirportData();
        //noinspection UnnecessaryLocalVariable
        Importer importer = new Importer(rawAirportData);

        this.importer = importer;
    }

    @Test
    void importAirports() {
        ArrayList<Airport> airports = importer.importAirports();
        assertEquals(7698, airports.size());
    }
}
