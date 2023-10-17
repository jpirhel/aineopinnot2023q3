package org.example.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for class Coord.
 */
class CoordTest {
    private Coord coord;

    @BeforeEach
    void setUp() {
        // City of Helsinki main railway station

        //noinspection UnnecessaryLocalVariable
        Coord coord = new Coord(60.17207950745159, 24.941344450549877);

        this.coord = coord;
    }

    /**
     * Tests the Latitude getter.
     */
    @Test
    void getLat() {
        assertEquals(this.coord.getLat(), 60.17207950745159);
    }

    /**
     * Tests the longitude getter.
     */
    @Test
    void getLon() {
        assertEquals(this.coord.getLon(), 24.941344450549877);
    }
}
