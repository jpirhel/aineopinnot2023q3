package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.*;

import org.example.data.Airport;
import org.example.data.AirportData;

//CHECKSTYLE.ON: AvoidStarImport

/**
 * Class for displaying the main window. Includes the map and left column route information.
 */
public class MainWindow {
    private final AirportData airportData;
    private final Map map;
    private final ArrayList<Airport> route;
    private final Boolean displayAirports;

    JFrame frame;

    JPanel panel;
    int defaultHeight = 800;
    int defaultWidth = 1200;

    /**
     * Constructor for the MainWindow object.
     * @param airportData The airport dataset.
     * @param route The route to be displayed between two airports
     * @param displayAirports If set, show all the airports and no route
     */
    public MainWindow(AirportData airportData, ArrayList<Airport> route, Boolean displayAirports) {
        this.airportData = airportData;
        this.route = route;
        this.displayAirports = displayAirports;

        frame = new JFrame("Route between airports");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // close window on ESC

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
            }
        });

        // set initial default size
        frame.setSize(defaultWidth, defaultHeight);

        // maximize the window
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        LayoutManager layout = new BorderLayout();

        panel = new JPanel(layout);
        frame.add(panel);

        if (!displayAirports) {
            // display left hand panel for the list of airports on the route
            LeftColumn leftColumn = new LeftColumn(route);
            panel.add(leftColumn.getPanel(), BorderLayout.LINE_START);
        }

        // initialize the map

        Map map = new Map(airportData, route, displayAirports);
        map.initMap();

        panel.add(map.getPanel(), BorderLayout.CENTER);

        this.map = map;
    }

    /**
     * Shows the main window by setting it visible.
     */
    public void show() {
        frame.setVisible(true);
    }
}
