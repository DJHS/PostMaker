package com.jeromecompsci.postmaker.swingui;

import com.jeromecompsci.postmaker.core.PostModel;
import com.jeromecompsci.postmaker.core.Publisher;
import com.jeromecompsci.postmaker.core.PublisherManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author derek
 */
public class ProgressPanel extends JPanel {
    private PostModel post;
    private PublisherManager publisherManager;
    private List<String> selectedPublisherNames;


    JPanel cardPanel;

    public ProgressPanel(PostModel post, PublisherManager publisherManager,
                         List<String> selectedPublisherNames) {
        this.post = post;
        this.publisherManager = publisherManager;
        this.selectedPublisherNames = selectedPublisherNames;
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                initLayout();
            }
        });
    }

    public void beginPublishing() {

    }

    static final int B = 16; // Borders around

    public void initLayout() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        cardPanel = new JPanel();
        BoxLayout clayout = new BoxLayout(cardPanel, BoxLayout.PAGE_AXIS);
        cardPanel.setLayout(clayout);

        for (String name : this.selectedPublisherNames) {
            Publisher publisher = this.publisherManager.getPublisherForName(name);
            ProgressCard card = new ProgressCard(name, publisher);
            cardPanel.add(card);
        }

        this.add(cardPanel);
        layout.putConstraint(SpringLayout.NORTH, cardPanel, B, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cardPanel, B, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, cardPanel, -B, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, cardPanel, -B, SpringLayout.SOUTH, this);
    }
}
