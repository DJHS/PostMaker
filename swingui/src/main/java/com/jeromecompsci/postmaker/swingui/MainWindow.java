package com.jeromecompsci.postmaker.swingui;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import java.io.IOException;
import java.util.List;

import com.jeromecompsci.postmaker.core.PostModel;
import com.jeromecompsci.postmaker.core.PublisherManager;


/**
 * @author derek
 */
public class MainWindow extends JFrame {
    PublisherManager publisherManager;

    public MainWindow() {
        super();
        publisherManager = new PublisherManager();
        initComponents();
        initLayout();
    }

    InputPanel inputPanel;
    ActionPanel actionPanel;
    ProgressPanel progressPanel = null;

    public PostModel getPostModel() {
        PostModel post = new PostModel();
        inputPanel.populatePostModel(post);
        return post;
    }

    public void doPerformPost() {
        List<String> publisherNames = actionPanel.getSelectedPublisherNames();
        PostModel post = this.getPostModel();
        ProgressPanel panel = new ProgressPanel(post, publisherManager, publisherNames);
        this.showProgressPanel(panel);
        try {
            panel.beginPublishing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInputPanel() {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                if (progressPanel != null) {
                    progressPanel.setVisible(false);
                    remove(progressPanel);
                    progressPanel = null;
                    inputPanel.setVisible(true);
                    add(inputPanel, BorderLayout.CENTER);
                }
            }
        });
    }

    public void showProgressPanel(final ProgressPanel panel) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                System.out.println("showProgressPanel() called.");
                if (progressPanel == null) {
                    actionPanel.setEnabled(false);
                    inputPanel.setVisible(false);
                    remove(inputPanel);
                    progressPanel = panel;
                    progressPanel.setVisible(true);
                    add(progressPanel, BorderLayout.CENTER);
                }
            }
        });
    }

    public void initComponents() {
        inputPanel = new InputPanel();
        actionPanel = new ActionPanel(this.publisherManager);
        actionPanel.registerPerformPostCallback(new ActionPanel.PerformPostCallback() {
            @Override public void performPost() {
                doPerformPost();
            }
        });
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
