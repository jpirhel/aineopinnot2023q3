package org.example;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.example.Main.main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;


//CHECKSTYLE.ON: AvoidStarImport

class MainTest {
    @Test
    void runIcaoCityMatches() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "helsinki"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);
    }

    @Test
    void runIcaoCountryMatches() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "finland"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);
    }

    @Test
    void runIcaoNoMatches() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "ZZZZZZ"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("No matches");

        assertEquals(true, matches);
    }

    @Test
    void runIcaoNoSearchString() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);
    }

    @Test
    void runIcaoNoDataSet() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "helsinki"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);
    }

    @Test
    void runIcaoFinlandDataSet() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "helsinki", "finland"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);
    }

    @Test
    void runIcaoBogusDataSet() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "helsinki", "bogus"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);
    }

    @Test
    void runPlanes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"planes"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Cessna");

        assertEquals(true, matches);
    }

    @Test
    void runNonExistentCommand() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"nonexistent"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);
    }

    @Test
    void runNullArgs() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = null;

        //noinspection ConstantValue
        main(args);

        String captured = out.toString();

        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);
    }

    @Test
    void runAirports() {
        String[] args = new String[]{"airports"};

        main(args);
    }

    @Test
    void runDijkstraWithFinlandDataSet() {
        String[] args = new String[]{"dijkstra", "EFHK", "EFRO", "500", "finland"};

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("total distance: 697 km, number of hops: 4");

        assertEquals(true, matches);
    }

    @Test
    void runDijkstraWithBogusDataSet() {
        String[] args = new String[]{"dijkstra", "EFHK", "EFRO", "500", "bogus"};

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("total distance: 697 km, number of hops: 4");

        assertEquals(true, matches);
    }

    @Test
    void runDijkstraWithRouteFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"dijkstra", "EFHK", "CYVR", "500"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Vancouver International");

        assertEquals(true, matches);
    }

    @Test
    void runDijkstraWithRouteNotFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"dijkstra", "EFHK", "CYVR", "300"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("No route found");

        assertEquals(true, matches);
    }

    @Test
    void runDijkstraWithNoParameters() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"dijkstra"};

        main(args);

        String captured = out.toString();
        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);
    }
}
