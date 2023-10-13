package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport
import javax.swing.*;
import java.awt.*;
//CHECKSTYLE.ON: AvoidStarImport
import javax.swing.border.EtchedBorder;

public class MainWindow {
    JFrame frame;

    JPanel panel;
    int defaultHeight = 800;
    int defaultWidth = 1200;
    public MainWindow() {
        frame = new JFrame("Airports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(defaultWidth, defaultHeight);
        // frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        Dimension d = frame.getSize();
        int height = (int) d.getHeight();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        frame.add(panel);

        LeftColumn leftColumn = new LeftColumn(height);
        panel.add(leftColumn.getPanel());

        RightColumn rightColumn = new RightColumn(height);
        panel.add(rightColumn.getPanel());
    }

    public void show() {
        frame.setVisible(true);
    }
}
