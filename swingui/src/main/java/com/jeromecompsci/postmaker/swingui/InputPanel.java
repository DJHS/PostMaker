package com.jeromecompsci.postmaker.swingui;

import com.jeromecompsci.postmaker.core.PostModel;

import javax.swing.*;
import java.awt.*;

/**
 * @author derek
 */
public class InputPanel extends JPanel {
    public InputPanel() {
        initComponents();
        initLayout();
    }

    public void populatePostModel(PostModel post) {
        post.titleSource = this.titleField.getText();
        post.fullTextSource = this.fullTextArea.getText();
        post.blurbTextSource = this.blurbTextArea.getText();
        post.categories = this.categoriesField.getText();
    }

    JTextField titleField;
    JTextArea fullTextArea;
    JTextArea blurbTextArea;
    JTextField categoriesField;

    public void initComponents() {
        titleField = new JTextField();
        Font defaultFont = titleField.getFont();
        titleField.setFont(new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize() + 2));
        fullTextArea = new JTextArea();
        blurbTextArea = new JTextArea();
        categoriesField = new JTextField();
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

        JLabel titleLabel = new JLabel("Title");
        JLabel fullTextLabel = new JLabel("Content");
        JLabel blurbLabel = new JLabel("Blurb");
        JLabel categoriesLabel = new JLabel("Categories");

        this.add(titleLabel);
        this.add(fullTextLabel);
        this.add(blurbLabel);
        this.add(categoriesLabel);

        this.add(titleField);
        this.add(fullTextArea);
        this.add(blurbTextArea);
        this.add(categoriesField);

        titleField.setPreferredSize(new Dimension(100, 68));
        titleField.setMaximumSize(titleField.getPreferredSize());
//        fullTextArea.setPreferredSize(new Dimension(400, 150));
//        blurbTextArea.setPreferredSize(new Dimension(400, 120));
        blurbTextArea.setPreferredSize(new Dimension(100, 120));
        blurbTextArea.setMaximumSize(blurbTextArea.getPreferredSize());
        categoriesField.setPreferredSize(new Dimension(100, 60));
        categoriesField.setMaximumSize(categoriesField.getPreferredSize());

        layout.putConstraint(NORTH, titleLabel, B, NORTH, this);
        layout.putConstraint(NORTH, titleField, S, SOUTH, titleLabel);
        layout.putConstraint(NORTH, fullTextLabel, S, SOUTH, titleField);
        layout.putConstraint(NORTH, fullTextArea, S, SOUTH, fullTextLabel);
        layout.putConstraint(NORTH, blurbLabel, S, SOUTH, fullTextArea);
        layout.putConstraint(NORTH, blurbTextArea, S, SOUTH, blurbLabel);
        layout.putConstraint(NORTH, categoriesLabel, S, SOUTH, blurbTextArea);
        layout.putConstraint(NORTH, categoriesField, S, SOUTH, categoriesLabel);

        System.out.println("blurbTextArea = " + blurbTextArea.getHeight());
        layout.putConstraint(SOUTH, this,
                (int) (4*S + B
                + blurbLabel.getPreferredSize().getHeight()
                + blurbTextArea.getPreferredSize().getHeight()
                + categoriesLabel.getPreferredSize().getHeight()
                + categoriesField.getPreferredSize().getHeight()),
                SOUTH, fullTextArea);

        layout.putConstraint(WEST, titleLabel, B, WEST, this);
        layout.putConstraint(WEST, titleField, B, WEST, this);
        layout.putConstraint(WEST, fullTextLabel, B, WEST, this);
        layout.putConstraint(WEST, fullTextArea, B, WEST, this);
        layout.putConstraint(WEST, blurbLabel, B, WEST, this);
        layout.putConstraint(WEST, blurbTextArea, B, WEST, this);
        layout.putConstraint(WEST, categoriesLabel, B, WEST, this);
        layout.putConstraint(WEST, categoriesField, B, WEST, this);

        layout.putConstraint(EAST, titleLabel, -B, EAST, this);
        layout.putConstraint(EAST, titleField, -B, EAST, this);
        layout.putConstraint(EAST, fullTextLabel, -B, EAST, this);
        layout.putConstraint(EAST, fullTextArea, -B, EAST, this);
        layout.putConstraint(EAST, blurbLabel, -B, EAST, this);
        layout.putConstraint(EAST, blurbTextArea, -B, EAST, this);
        layout.putConstraint(EAST, categoriesLabel, -B, EAST, this);
        layout.putConstraint(EAST, categoriesField, -B, EAST, this);

    }
}
