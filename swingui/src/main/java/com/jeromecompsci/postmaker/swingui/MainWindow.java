package com.jeromecompsci.postmaker.swingui;

import javax.swing.*;
import com.jeromecompsci.postmaker.core.PostModel;

import java.awt.*;

/**
 * @author derek
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        super();
        initComponents();
        initLayout();
    }

    InputPanel inputPanel;
    ActionPanel actionPanel;

    public void initComponents() {
        inputPanel = new InputPanel();
        actionPanel = new ActionPanel();
    }

    public void initLayout() {
        Container contentPane = this.getContentPane();
        BorderLayout layout = new BorderLayout();
        contentPane.setLayout(layout);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(actionPanel, BorderLayout.LINE_END);

        this.pack();
        this.setMinimumSize(new Dimension(1200, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PostMaker");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // Do Nothing (it will fall back to default LnF)
                }
                new MainWindow().setVisible(true);
            }
        });
    }
}
