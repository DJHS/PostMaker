package com.jeromecompsci.postmaker.core;

import java.io.File;
import java.util.List;
import java.util.Properties;
import org.apache.commons.csv.CSVRecord;

/**
 * @author derek
 */
public class PostModel {
    static String BLURB_STRUCTURE = "%s. See %s for more details."; // hardcoded structure for now
    String titleSource;
    String fullTextSource;
    String blurbTextSource;
    String primaryPresenceLink;
    EventModel event;

    List<CSVRecord> userList; // hardcoded to CSVRecord for now.
    Properties privProperties;

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
        this.renderedFullText = fullTextRenderer.getRenderedString(this.fullTextSource);
        this.renderedBlurb = blurbRenderer.getRenderedString(String.format(BLURB_STRUCTURE, this.blurbTextSource, this.primaryPresenceLink));
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

    public void loadPropertiesFromFile(File propertiesFile) {

    }

    public void loadUserListFromFile(File userListFile) {

    }

}
