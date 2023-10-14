package org.example.gui;

import java.util.ArrayList;

//CHECKSTYLE.OFF: AvoidStarImport
import javax.swing.*;
import java.awt.*;
//CHECKSTYLE.ON: AvoidStarImport

import org.example.data.Airport;
import org.example.data.AirportData;

public class MainWindow {
    private final AirportData airportData;
    private final Map map;
    private final ArrayList<Airport> route;

    JFrame frame;

    JPanel panel;
    int defaultHeight = 800;
    int defaultWidth = 1200;
    public MainWindow(AirportData airportData, ArrayList<Airport> route) {
        this.airportData = airportData;
        this.route = route;

        frame = new JFrame("Route between airports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(defaultWidth, defaultHeight);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        Dimension d = frame.getSize();
        int height = (int) d.getHeight();

        LayoutManager layout = new BorderLayout();

        panel = new JPanel(layout);
        frame.add(panel);

        LeftColumn leftColumn = new LeftColumn((int) frame.getSize().getHeight(), route);
        panel.add(leftColumn.getPanel(), BorderLayout.LINE_START);

        Map map = new Map(airportData, route);
        map.initMap();
        panel.add(map.getPanel(), BorderLayout.CENTER);

        this.map = map;
    }

    public void show() {
        frame.setVisible(true);
    }
}
