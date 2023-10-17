package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.example.data.Airport;
import org.example.geo.GeoUtil;

import static org.example.gui.GuiUtil.*;

//CHECKSTYLE.ON: AvoidStarImport

public class LeftColumn {
    JPanel panel;

    int height;
    private ArrayList<Airport> route;

    /**
     * @param height The window height
     * @param route The route to be displayed
     */
    public LeftColumn(int height, ArrayList<Airport> route) {
        this.height = height;
        this.route = route;

        refresh();
    }

    /**
     * @return JPanel object for the left column
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Refresh the display panel.
     */
    public void refresh() {
        panel = new JPanel();

        panel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, height));
        panel.setBackground(Color.WHITE);

        int totalDistance = GeoUtil.routeTotalDistance(route, false);

        // route information header

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, HEADER_LABEL_HEIGHT));
        JLabel headerLabel = new JLabel("Route distance: " + totalDistance + " km, hops: " + route.size());
        headerPanel.add(headerLabel);

        // panel for the calculated route

        JPanel routePanel = new JPanel();
        routePanel.setLayout(new BoxLayout(routePanel, BoxLayout.Y_AXIS));
        routePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        routePanel.setBackground(Color.WHITE);

        routePanel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, height + 500));

        // header information

        JLabel routeHeader = new JLabel("(distance to next hop) airport data");
        routePanel.add(routeHeader);

        // empty label used as a divider

        JLabel empty = new JLabel(" ");
        routePanel.add(empty);

        Airport start = route.get(0);

        // start airport

        String text = "(START) " + start.getName() + ", " + start.getCity() + ", " + start.getCountry();
        JLabel airportLabel = new JLabel(text);
        routePanel.add(airportLabel);

        int distanceToPrevHop;

        if (route != null) {
            // display information on hops, including distance to previous hop

            for (int i = 1; i < route.size() - 2; i++) {
                Airport prev = route.get(i - 1);
                Airport a = route.get(i);

                distanceToPrevHop = GeoUtil.distanceInKm(a.getCoord(), prev.getCoord());

                text = "(" + distanceToPrevHop + " km) " + a.getName() + ", " + a.getCity() + ", " + a.getCountry();
                airportLabel = new JLabel(text);
                routePanel.add(airportLabel);
            }

            // display information on destination airport

            Airport prev = route.get(route.size() - 2);
            Airport dest = route.get(route.size() - 1);

            int distanceToLastHop = GeoUtil.distanceInKm(prev.getCoord(), dest.getCoord());
            text = "(" + distanceToLastHop + "km) " + dest.getName() + ", " + dest.getCity() + ", " + dest.getCountry();

            airportLabel = new JLabel(text);
            routePanel.add(airportLabel);

            routePanel.revalidate();
        }

        panel.add(headerPanel);
        panel.add(routePanel);
    }
}
