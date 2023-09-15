package org.example.data;

//CHECKSTYLE.OFF: AvoidStarImport
import static org.junit.jupiter.api.Assertions.*;
//CHECKSTYLE.ON: AvoidStarImport

import org.example.geo.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AirportTest {
    @SuppressWarnings({"checkstyle:LineLength"})
    private final String airportDataLine = "421,\"Helsinki Vantaa Airport\",\"Helsinki\",\"Finland\",\"HEL\",\"EFHK\",60.317199707031,24.963300704956,179,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"";

    @SuppressWarnings({"checkstyle:LineLength", "FieldCanBeLocal"})
    private final String notAirportDataLine = "421,\"Helsinki Vantaa Airport\",\"Helsinki\",\"Finland\",\"HEL\",\"EFHK\",60.317199707031,24.963300704956,179,2,\"E\",\"Europe/Helsinki\",\"not_airport\",\"OurAirports\"";

    @SuppressWarnings({"checkstyle:LineLength", "FieldCanBeLocal"})
    private final String longAirportDataLine = "641,\"Harstad/Narvik Airport, Evenes\",\"Harstad/Narvik\",\"Norway\",\"EVE\",\"ENEV\",68.491302490234,16.678100585938,84,1,\"E\",\"Europe/Oslo\",\"airport\",\"OurAirports\"";

    private Airport airport;

    @BeforeEach
    void setUp() {
        //noinspection UnnecessaryLocalVariable
        Airport airport = Airport.fromLine(1, airportDataLine);

        this.airport = airport;
    }

    @Test
    void fromLine() {
        Airport airport2 = Airport.fromLine(1, airportDataLine);

        assertEquals(airport, airport2);

        //noinspection ConstantValue
        Airport airport3 = Airport.fromLine(1, null);

        //noinspection ConstantValue
        assertNull(airport3);

        Airport airport4 = Airport.fromLine(1, notAirportDataLine);

        assertNull(airport4);

        Airport airport5 = Airport.fromLine(1, longAirportDataLine);

        @SuppressWarnings({"checkstyle:LineLength"})
        Airport airport5correct = new Airport(1, 641, "Harstad/Narvik Airport, Evenes", "Harstad/Narvik", "Norway", "ENEV", 68.491302490234, 16.678100585938);

        assertEquals(airport5, airport5correct);
    }

    @Test
    void getId() {
        assertEquals(1, airport.getId());
    }

    @Test
    void getName() {
        assertEquals("Helsinki Vantaa Airport", airport.getName());
    }

    @Test
    void getCity() {
        assertEquals("Helsinki", airport.getCity());
    }

    @Test
    void getCountry() {
        assertEquals("Finland", airport.getCountry());
    }

    @Test
    void getIcao() {
        assertEquals("EFHK", airport.getIcao());
    }

    @Test
    void getLatitude() {
        assertEquals(60.317199707031, airport.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(24.963300704956, airport.getLongitude());
    }

    @Test
    void getCoord() {
        Coord coord = new Coord(60.317199707031, 24.963300704956);
        assertEquals(coord, airport.getCoord());
    }
}
