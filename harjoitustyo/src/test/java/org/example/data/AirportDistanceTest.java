package org.example.data;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class AirportDistanceTest {
    private AirportDistance airportDistance;
    private Airport airportFrom;
    private Airport airportTo;

    @SuppressWarnings({"checkstyle:LineLength"})
    @BeforeEach
    void setUp() {
        Airport airportFrom = Airport.fromLine(1, "421,\"Helsinki Vantaa Airport\",\"Helsinki\",\"Finland\",\"HEL\",\"EFHK\",60.317199707031,24.963300704956,179,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        Airport airportTo = Airport.fromLine(2, "458,\"Tampere-Pirkkala Airport\",\"Tampere\",\"Finland\",\"TMP\",\"EFTP\",61.414100646973,23.604400634766,390,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");

        this.airportFrom = airportFrom;
        this.airportTo = airportTo;

        this.airportDistance = new AirportDistance(airportFrom, airportTo, 143);
    }

    @SuppressWarnings({"checkstyle:LineLength"})
    @Test
    void getFromAirport() {
        Airport airportFromCorrect = new Airport(1, 421, "Helsinki Vantaa Airport", "Helsinki", "Finland", "EFHK", 60.317199707031,24.963300704956);
        assertEquals(airportFromCorrect, airportDistance.getFromAirport());
    }

    @SuppressWarnings({"checkstyle:LineLength"})
    @Test
    void getToAirport() {
        Airport airportFromCorrect = new Airport(2, 458, "Tampere-Pirkkala Airport", "Tampere", "Finland", "EFTP", 61.414100646973,23.604400634766);

        assertEquals(airportFromCorrect, airportDistance.getToAirport());
    }

    @Test
    void getDistance() {
        assertEquals(143, airportDistance.getDistance());
    }

    @Test
    void compareTo() {
        AirportDistance d1 = new AirportDistance(airportFrom, airportTo, 100);
        AirportDistance d2 = new AirportDistance(airportFrom, airportTo, 100);
        AirportDistance d3 = new AirportDistance(airportFrom, airportTo, 101);
        AirportDistance d4 = new AirportDistance(airportFrom, airportTo, 99);

        assertEquals(0, d1.compareTo(d2));
        assertEquals(-1, d1.compareTo(d3));
        assertEquals(1, d1.compareTo(d4));
    }
}
