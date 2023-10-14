package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport

import static org.example.gui.GuiUtil.BUTTON_HEIGHT;
import static org.example.gui.GuiUtil.LEFT_COLUMN_WIDTH;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import org.example.data.Airport;

//CHECKSTYLE.ON: AvoidStarImport

public class LeftColumn {
    JPanel panel;

    int height;
    private ArrayList<Airport> route;

    public LeftColumn(int height, ArrayList<Airport> route) {
        this.height = height;
        this.route = route;

        refresh();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refresh() {
        panel = new JPanel();

        panel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, height));
        panel.setBackground(Color.LIGHT_GRAY);

        JPanel label1Panel = new JPanel();
        JLabel label1 = new JLabel("Leftcolumn 1");
        label1Panel.add(label1);

        JPanel routePanel = new JPanel();
        routePanel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, BUTTON_HEIGHT));

        if (route != null) {
            for (Airport airport : route) {
                String text = airport.getIcao();
                JLabel airportLabel = new JLabel(text);
                routePanel.add(airportLabel);
                routePanel.revalidate();
            }
        }

        panel.add(label1Panel);
        panel.add(routePanel);
    }
}
