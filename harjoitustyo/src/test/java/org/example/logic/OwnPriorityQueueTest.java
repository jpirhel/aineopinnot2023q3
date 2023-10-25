package org.example.logic;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.example.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//CHECKSTYLE.ON: AvoidStarImport

class OwnPriorityQueueTest {
    private final ArrayList<AirportDistance> distances;
    private OwnPriorityQueue queue;

    public OwnPriorityQueueTest() {
        int fromAirportId = 417; // Helsinki-Vantaa
        int rangeInKm = 500;

        RawAirportData rawAirportData = new RawAirportDataWorld();

        Importer importer = new Importer(rawAirportData);
        ArrayList<Airport> airports = importer.importAirports();

        AirportDataGenerator airportDataGenerator = new AirportDataGenerator(airports);

        AirportData airportData = airportDataGenerator.getAirportData();

        Airport airportFrom = airportData.getAirports().get(fromAirportId);

        AirportGraph airportGraph = airportDataGenerator.generateAirportGraph(
                airportFrom,
                rangeInKm);

        ArrayList<AirportDistance> distances = airportGraph.getGraph()[fromAirportId];
        this.distances = distances;
    }

    @BeforeEach
    void setUp() {
        int size = distances.size();
        this.queue = new OwnPriorityQueue(size);
    }

    @Test
    void addAllDistances() {
        for (AirportDistance distance: distances) {
            queue.add(distance);
        }

        assertEquals(distances.size(), queue.size());
    }

    @Test
    void add() {
        AirportDistance airportDistance = distances.get(0);

        queue.add(airportDistance);

        int size = queue.size();

        assertEquals(1, size);
    }

    @Test
    void addNull() {
        AirportDistance airportDistance = null;

        //noinspection ConstantValue
        queue.add(airportDistance);
    }

    @Test
    void isEmpty() {
        boolean empty = queue.isEmpty();
        assertTrue(empty);
    }

    @Test
    void poll() {
        for (AirportDistance distance: distances) {
            queue.add(distance);
        }

        AirportDistance smallest = queue.poll();

        assertNotNull(smallest);

        int smallestDistance = smallest.getDistance();

        assertEquals(8, smallestDistance);
    }

    @Test
    void pollEmpty() {
        AirportDistance smallest = queue.poll();
        assertNull(smallest);
    }
}
