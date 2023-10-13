package org.example.gui;

//CHECKSTYLE.OFF: AvoidStarImport
import java.awt.*;
import javax.swing.*;
//CHECKSTYLE.ON: AvoidStarImport

public class RightColumn {
    JPanel panel;

    int height;

    public RightColumn(int height) {
        this.height = height;

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, height));
        panel.setBackground(Color.RED);
        JButton button = new JButton(new Integer(height).toString());
        panel.add(button);
    }

    public JPanel getPanel() {
        return panel;
    }
}
