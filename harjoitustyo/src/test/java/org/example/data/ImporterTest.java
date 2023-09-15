package org.example.data;

//CHECKSTYLE.OFF: AvoidStarImport
import static org.junit.jupiter.api.Assertions.*;
//CHECKSTYLE.ON: AvoidStarImport

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ImporterTest {
    private final Importer importer;

    public ImporterTest() {
        String filename = "../data/airports.dat";

        //noinspection UnnecessaryLocalVariable
        Importer importer = new Importer(filename);

        this.importer = importer;
    }

    @Test
    void importAirports() {
        ArrayList<Airport> airports = importer.importAirports();
        assertEquals(7698, airports.size());
    }

    @Test
    void failedImportAirports() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Importer failingImporter = new Importer("filename_does_not_exist");
            ArrayList<Airport> airports = failingImporter.importAirports();
        });
    }
}
