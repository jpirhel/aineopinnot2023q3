package org.example.geo;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class CoordTest {
    private Coord coord;

    @BeforeEach
    void setUp() {
        // City of Helsinki main railway station

        //noinspection UnnecessaryLocalVariable
        Coord coord = new Coord(60.17207950745159, 24.941344450549877);

        this.coord = coord;
    }

    @Test
    void getLat() {
        assertEquals(this.coord.getLat(), 60.17207950745159);
    }

    @Test
    void getLon() {
        assertEquals(this.coord.getLon(), 24.941344450549877);
    }
}
