package com.jeromecompsci.postmaker.core;

import java.util.Properties;

/**
 * @author derek
 */
public abstract class Publisher {
    public abstract void publish(PostModel post);

    private PublishingListener publishingListener;

    public void registerPublishingListener(PublishingListener listener) {
        this.publishingListener = listener;
    }

    protected void initProgress(int max) {
        if (publishingListener != null)
            publishingListener.progressInitialized(max);
    }

    protected void updateProgress(int progress) {
        if (publishingListener != null)
            publishingListener.progressUpdated(progress);
    }

    protected void updateInfo(String info) {
        if (publishingListener != null)
            publishingListener.infoUpdated(info);
    }

    protected void declareDone() {
        if (publishingListener != null)
            publishingListener.done();
    }

    protected void throwException(Exception e) {
        if (publishingListener != null)
            publishingListener.exceptionThrown(e);
    }

}
