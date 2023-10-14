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

public class MainWindow {
    private final AirportData airportData;
    private final Map map;
    private final ArrayList<Airport> route;
    private final Boolean displayAirports;

    JFrame frame;

    JPanel panel;
    int defaultHeight = 800;
    int defaultWidth = 1200;

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

        frame.setSize(defaultWidth, defaultHeight);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        // Dimension d = frame.getSize();
        // int height = (int) d.getHeight();

        LayoutManager layout = new BorderLayout();

        panel = new JPanel(layout);
        frame.add(panel);

        if (!displayAirports) {
            LeftColumn leftColumn = new LeftColumn((int) frame.getSize().getHeight(), route);
            panel.add(leftColumn.getPanel(), BorderLayout.LINE_START);
        }

        Map map = new Map(airportData, route, displayAirports);
        map.initMap();

        panel.add(map.getPanel(), BorderLayout.CENTER);

        this.map = map;
    }

    public void show() {
        frame.setVisible(true);
    }
}
