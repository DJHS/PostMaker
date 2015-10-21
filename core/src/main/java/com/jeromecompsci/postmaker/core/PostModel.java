package com.jeromecompsci.postmaker.core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.jeromecompsci.postmaker.core.fieldrenderers.MarkdownHTMLRenderer;
import com.jeromecompsci.postmaker.core.fieldrenderers.NullRenderer;

/**
 * @author derek
 */
public class PostModel {
    static String BLURB_STRUCTURE = "%s. See %s for more details."; // hardcoded structure for now
    static String DEFAULT_PRIV_PROPERTIES_LOCATION = "priv.properties";
    static String DEFAULT_USER_LIST_LOCATION = "users.csv";

    String titleSource;
    String fullTextSource;
    String blurbTextSource;
    String primaryPresenceLink;
    EventModel event;

    List<CSVRecord> userList; // hardcoded to CSVRecord for now.
    Properties privProperties = new Properties();

    private String renderedTitle;
    private String renderedFullText;
    private String renderedBlurb;

    public PostModel() {

    }

    public void associateEvent(EventModel event) {
        this.event = event;
    }

    /**
     * Default render() method with no customization of rendering.
     */
    public void render() {
        this.render(NullRenderer.INSTANCE, MarkdownHTMLRenderer.INSTANCE, NullRenderer.INSTANCE);
    }

    /**
     * Default load...() method that loads privProperties, userList, etc. from default locations
     */
    public void loadAllExternalResources() throws IOException {
        this.loadPropertiesFromFile(new File(DEFAULT_PRIV_PROPERTIES_LOCATION));
        this.loadUserListFromFile(new File(DEFAULT_USER_LIST_LOCATION));
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

    public void render(FieldRenderer titleRenderer, FieldRenderer fullTextRenderer, FieldRenderer blurbRenderer) {
        this.renderedTitle = titleRenderer.getRenderedString(this.titleSource);
        this.renderedFullText = fullTextRenderer.getRenderedString(this.fullTextSource);
        this.renderedBlurb = blurbRenderer.getRenderedString(String.format(BLURB_STRUCTURE, this.blurbTextSource, this.primaryPresenceLink));
    }


    public void loadPropertiesFromFile(File propertiesFile) throws IOException {
        privProperties.load(new FileReader(propertiesFile));
    }

    public void loadUserListFromFile(File userListFile) throws IOException {
        CSVParser parser = CSVParser.parse(userListFile, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
        this.userList = parser.getRecords();
    }

}
