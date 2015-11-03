package com.jeromecompsci.postmaker.swingui;

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

    JTextField titleField;
    JTextArea fullTextArea;
    JTextArea blurbTextArea;
    JTextField categoriesField;

    public void initComponents() {
        titleField = new JTextField();
        fullTextArea = new JTextArea();
        blurbTextArea = new JTextArea();
        categoriesField = new JTextField();
    }

    public void initLayout() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);

        this.add(new JLabel("Title"));
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        this.add(titleField);
        this.add(new JLabel("Content"));
        this.add(fullTextArea);
        this.add(new JLabel("Blurb"));
        this.add(blurbTextArea);
        this.add(new JLabel("Categories"));
        categoriesField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        this.add(categoriesField);
    }
}
