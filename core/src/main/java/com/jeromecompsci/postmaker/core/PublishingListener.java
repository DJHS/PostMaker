package com.jeromecompsci.postmaker.core;

import java.beans.ExceptionListener;

/**
 * @author derek
 */
public interface PublishingListener extends ExceptionListener {
    void progressInitialized(int max);
    void progressUpdated(int progress);
    void infoUpdated(String info);
    void done();
}
