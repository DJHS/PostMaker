package com.jeromecompsci.postmaker.swingui;

import javax.swing.*;
import java.awt.*;

/**
 * @author derek
 */
public class ActionPanel extends JPanel {
    public ActionPanel() {
        initComponents();
        initLayout();
    }

    JButton makePostButton;


    public void initComponents() {
        makePostButton = new JButton("Make Post!");
    }

    static final String NORTH = SpringLayout.NORTH;
    static final String SOUTH = SpringLayout.SOUTH;
    static final String EAST = SpringLayout.EAST;
    static final String WEST = SpringLayout.WEST;

    static final int B = 12; // Border (margin from edges)
    static final int S = 4; // Spacing (between components)

    public void initLayout() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel postToLabel = new JLabel("Post to");

        this.add(postToLabel);
        this.add(makePostButton);

        layout.putConstraint(NORTH, postToLabel, B, NORTH, this);
        layout.putConstraint(SOUTH, makePostButton, -B, SOUTH, this);

        layout.putConstraint(WEST, postToLabel, B, WEST, this);
        layout.putConstraint(WEST, makePostButton, B, WEST, this);

        layout.putConstraint(EAST, postToLabel, -B, EAST, this);
        layout.putConstraint(EAST, makePostButton, -B, EAST, this);

        this.setPreferredSize(new Dimension(380, 300));
    }
}
