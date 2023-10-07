package org.example.gui;

import javax.swing.*;
import java.awt.*;


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
