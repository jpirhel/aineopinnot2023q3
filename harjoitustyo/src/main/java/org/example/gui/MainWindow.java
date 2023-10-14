package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport
import org.example.data.Airport;
import org.example.data.AirportData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//CHECKSTYLE.ON: AvoidStarImport
import javax.swing.border.EtchedBorder;

public class MainWindow {
    private final AirportData airportData;
    private final Map map;
    JFrame frame;

    JPanel panel;
    int defaultHeight = 800;
    int defaultWidth = 1200;
    public MainWindow(AirportData airportData) {
        this.airportData = airportData;
        
        frame = new JFrame("Airports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(defaultWidth, defaultHeight);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        Dimension d = frame.getSize();
        int height = (int) d.getHeight();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        frame.add(panel);

        Map map = new Map(airportData);
        map.initMap();
        frame.add(map.getPanel());

        this.map = map;

//        LeftColumn leftColumn = new LeftColumn(height);
//        panel.add(leftColumn.getPanel());
//
//        RightColumn rightColumn = new RightColumn(height);
//        panel.add(rightColumn.getPanel());
    }

    public void show() {
        frame.setVisible(true);
    }

    public void setMapRoute(ArrayList<Airport> route) {
        map.setRoute(route);
    }
}
