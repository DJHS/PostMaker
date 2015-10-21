package com.jeromecompsci.postmaker.core;

import java.net.URL;
import java.util.Date;

/**
 * @author derek
 */
public class PostModel {
    String titleSource;
    String fullTextSource;
    String blurbTextSource;
    String primaryPresenceLink;
    EventModel event;

    private String renderedTitle;
    private String renderedFullText;
    private String renderedBlurb;

    public PostModel() {

    }

    public void associateEvent(EventModel event) {
        this.event = event;
    }

    public void render(FieldRenderer titleRenderer, FieldRenderer fullTextRenderer, FieldRenderer blurbRenderer) {
        this.renderedTitle = titleRenderer.getRenderedString(this.titleSource);
        this.renderedFullText = fullTextRenderer.getRenderedString(this.titleSource);
        this.renderedBlurb = blurbRenderer.getRenderedString(this.blurbTextSource);
    }

    public String getRenderedTitle() {
        return renderedTitle;
    }

    public String getRenderedFullText() {
        return renderedFullText;
    }

    public String getRenderedBlurb() {
        return renderedBlurb;
    }

}
