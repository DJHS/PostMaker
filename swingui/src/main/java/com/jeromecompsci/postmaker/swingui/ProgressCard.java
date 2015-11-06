package com.jeromecompsci.postmaker.swingui;

import com.jeromecompsci.postmaker.core.PostModel;
import com.jeromecompsci.postmaker.core.Publisher;
import com.jeromecompsci.postmaker.core.PublishingListener;

import javax.swing.*;
import java.awt.*;

/**
 * @author derek
 */
public class ProgressCard extends JPanel implements PublishingListener {
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

    public void doPublish(final PostModel post) {
        System.out.println("doPublish() of ProgressCard called.");
        this.publisher.registerPublishingListener(this);
        publisher.publish(post);
//        Thread t = new Thread(new Runnable() {
//            @Override public void run() {
//                publisher.publish(post);
//            }
//        });
//        t.start();
    }

    @Override public void progressInitialized(final int max) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                if (max < 0) {
                    bar.setIndeterminate(true);
                } else {
                    bar.setMaximum(max);
                }
            }
        });
    }

    @Override public void progressUpdated(final int progress) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                bar.setValue(progress);
            }
        });
    }

    @Override public void infoUpdated(final String info) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                infoLabel.setText(info);
            }
        });
    }

    @Override public void done() {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                bar.setIndeterminate(false);
                bar.setValue(bar.getMaximum());
                infoLabel.setText("Done!");
            }
        });
    }

    @Override public void exceptionThrown(Exception e) {
        e.printStackTrace();
    }
}
