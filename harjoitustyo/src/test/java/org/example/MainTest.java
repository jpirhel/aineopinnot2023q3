package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void runIcaoCityMatches() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "helsinki"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);

        assertEquals(0, exitCode);
    }

    @Test
    void runIcaoCountryMatches() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "finland"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Helsinki Vantaa");

        assertEquals(true, matches);

        assertEquals(0, exitCode);
    }

    @Test
    void runIcaoNoMatches() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao", "ZZZZZZ"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("No matches");

        assertEquals(true, matches);

        assertEquals(0, exitCode);
    }

    @Test
    void runIcaoNoSearchString() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"icao"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);

        assertEquals(1, exitCode);
    }

    @Test
    void runPlanes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"planes"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Cessna");

        assertEquals(true, matches);

        assertEquals(0, exitCode);
    }

    @Test
    void runNonExistentCommand() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"nonexistent"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);

        assertEquals(1, exitCode);
    }

    @Test
    void runNullArgs() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = null;

        //noinspection ConstantValue
        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();

        Boolean matches = captured.contains("Usage:");

        assertEquals(1, exitCode);
    }

    @Test
    void runAirports() {
        String[] args = new String[]{"airports"};

        Main main = new Main(args);
        int exitCode = main.run();

        assertEquals(0, exitCode); // succeeded
    }


    @Test
    void runDijkstraWithRouteFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"dijkstra", "EFHK", "CYVR", "500"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Vancouver International");

        assertEquals(true, matches);

        assertEquals(0, exitCode);
    }

    @Test
    void runDijkstraWithRouteNotFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"dijkstra", "EFHK", "CYVR", "300"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("No route found");

        assertEquals(true, matches);

        assertEquals(0, exitCode); // no route found is still a success
    }

    @Test
    void runDijkstraWithNoParameters() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = new String[]{"dijkstra"};

        Main main = new Main(args);
        int exitCode = main.run();

        String captured = out.toString();
        Boolean matches = captured.contains("Usage:");

        assertEquals(true, matches);

        assertEquals(1, exitCode);
    }
}
