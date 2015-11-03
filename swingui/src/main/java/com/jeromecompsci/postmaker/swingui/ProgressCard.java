package com.jeromecompsci.postmaker.swingui;

import com.jeromecompsci.postmaker.core.Publisher;

import javax.swing.*;
import java.awt.*;

/**
 * @author derek
 */
public class ProgressCard extends JPanel {
    String publisherName;
    Publisher publisher;

    public ProgressCard(String publisherName, Publisher publisher) {
        this.publisherName = publisherName;
        this.publisher = publisher;
        initView();
    }

    JLabel nameLabel;
    JProgressBar bar;
    JLabel infoLabel;

    public void initView() {
        System.out.println("Initing the view for: " + publisherName);
        nameLabel = new JLabel(publisherName);
        Font defaultFont = nameLabel.getFont();
        nameLabel.setFont(new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize()));
        bar = new JProgressBar();
        infoLabel = new JLabel("Info info info info");

        this.setLayout(new GridLayout(3, 1));

        this.add(nameLabel);
        this.add(bar);
        this.add(infoLabel);

        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
    }

    public void doPublish() {

    }
}
