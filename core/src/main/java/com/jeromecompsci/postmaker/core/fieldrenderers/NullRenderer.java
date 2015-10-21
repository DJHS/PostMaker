package com.jeromecompsci.postmaker.core.fieldrenderers;

import com.jeromecompsci.postmaker.core.FieldRenderer;

/**
 * @author derek
 */
public enum NullRenderer implements FieldRenderer{
    INSTANCE;
    public String getRenderedString(String rawString) {
        return rawString;
    }
}
