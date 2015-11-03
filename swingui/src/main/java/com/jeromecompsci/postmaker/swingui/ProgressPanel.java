package com.jeromecompsci.postmaker.swingui;

import com.jeromecompsci.postmaker.core.PostModel;
import com.jeromecompsci.postmaker.core.PublisherManager;

import javax.swing.*;
import java.util.List;

/**
 * @author derek
 */
public class ProgressPanel extends JPanel {
    private PostModel post;
    private PublisherManager publisherManager;
    private List<String> selectedPublisherNames;

    public ProgressPanel(PostModel post, PublisherManager publisherManager,
                         List<String> selectedPublisherNames) {
        this.post = post;
        this.publisherManager = publisherManager;
        this.selectedPublisherNames = selectedPublisherNames;
    }

    public void beginPublishing() {

    }
}
