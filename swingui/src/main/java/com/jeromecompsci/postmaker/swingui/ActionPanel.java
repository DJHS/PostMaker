package com.jeromecompsci.postmaker.swingui;

import com.jeromecompsci.postmaker.core.PublisherManager;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author derek
 */
public class ActionPanel extends JPanel {
    private PublisherManager publisherManager;
    private PerformPostCallback performPostCallback = null;

    public ActionPanel(PublisherManager publisherManager) {
        this.publisherManager = publisherManager;
        initComponents();
        initLayout();
    }

    interface PerformPostCallback {
        void performPost();
    }

    public void registerPerformPostCallback(PerformPostCallback callback) {
        this.performPostCallback = callback;
    }

    public List<String> getSelectedPublisherNames() {
        List<String> result = new ArrayList<String>();
        for (int i=0; i<checkboxPanel.getComponentCount(); i++) {
            JCheckBox checkBox = (JCheckBox) checkboxPanel.getComponent(i);
            if (checkBox.isSelected()) {
                result.add(checkBox.getText());
            }
        }
        return result;
    }

    JButton makePostButton;
    JPanel checkboxPanel;

    public void initComponents() {
        makePostButton = new JButton("Make Post!");
        makePostButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent actionEvent) {
                if (performPostCallback != null) {
                    performPostCallback.performPost();
                }
            }
        });

        checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.PAGE_AXIS));

        for (String publisherName : publisherManager.getAllPublisherNames()) {
            JCheckBox checkBox = new JCheckBox(publisherName);
            checkBox.setSelected(true);
            checkboxPanel.add(checkBox);
        }
    }

    static final String NORTH = SpringLayout.NORTH;
    static final String SOUTH = SpringLayout.SOUTH;
    static final String EAST = SpringLayout.EAST;
    static final String WEST = SpringLayout.WEST;

    static final int B = 12; // Border (margin from edges)
    static final int S = 4; // Spacing (between components)
    static final int INDENT = B + 14; // Indent in from border

    public void initLayout() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel postToLabel = new JLabel("Post to");

        this.add(postToLabel);
        this.add(makePostButton);
        this.add(checkboxPanel);

        makePostButton.setPreferredSize(new Dimension(100, 60));
        makePostButton.setMaximumSize(makePostButton.getPreferredSize());

        layout.putConstraint(NORTH, postToLabel, B, NORTH, this);
        layout.putConstraint(NORTH, checkboxPanel, S, SOUTH, postToLabel);
        layout.putConstraint(SOUTH, makePostButton, -B, SOUTH, this);

        layout.putConstraint(WEST, postToLabel, B, WEST, this);
        layout.putConstraint(WEST, checkboxPanel, INDENT, WEST, this);
        layout.putConstraint(WEST, makePostButton, B, WEST, this);

        layout.putConstraint(EAST, postToLabel, -B, EAST, this);
        layout.putConstraint(EAST, checkboxPanel, -B, EAST, this);
        layout.putConstraint(EAST, makePostButton, -B, EAST, this);

        this.setPreferredSize(new Dimension(380, 300));
    }
}
