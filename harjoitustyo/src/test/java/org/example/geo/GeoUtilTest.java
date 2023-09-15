package org.example.geo;

//CHECKSTYLE.OFF: AvoidStarImport
import static org.junit.jupiter.api.Assertions.*;
//CHECKSTYLE.ON: AvoidStarImport

import org.junit.jupiter.api.Test;

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
}
