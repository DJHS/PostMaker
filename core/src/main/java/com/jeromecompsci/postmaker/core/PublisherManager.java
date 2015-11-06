package com.jeromecompsci.postmaker.core;

import com.jeromecompsci.postmaker.core.publishers.*;

import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @author derek
 */
public class PublisherManager {

    private Map<String, Publisher> publisherMap = new LinkedHashMap<String, Publisher>();

    public PublisherManager() {
        publisherMap.put("WordPress", new WordpressPublisher());
    }

    public Set<String> getAllPublisherNames() {
        return publisherMap.keySet();
    }

    public Publisher getPublisherForName(String name) {
        return publisherMap.get(name);
    }
}
