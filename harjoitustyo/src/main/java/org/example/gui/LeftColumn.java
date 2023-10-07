package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class LeftColumn {
    JPanel panel;

    int height;

    public LeftColumn(int height) {
        this.height = height;

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, height));
        panel.setBackground(Color.BLUE);
        JButton button = new JButton("LeftColumn");
        panel.add(button);
    }

    public JPanel getPanel() {
        return panel;
    }
}
