package com.jeromecompsci.postmaker.core.fieldrenderers;

import com.jeromecompsci.postmaker.core.FieldRenderer;

/**
 * @author derek
 */
public class NullRenderer implements FieldRenderer{
    public String getRenderedString(String rawString) {
        return rawString;
    }
}
