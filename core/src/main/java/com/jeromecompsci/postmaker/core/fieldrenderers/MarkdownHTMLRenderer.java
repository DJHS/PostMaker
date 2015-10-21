package com.jeromecompsci.postmaker.core.fieldrenderers;

import com.jeromecompsci.postmaker.core.FieldRenderer;
import org.pegdown.PegDownProcessor;

/**
 * @author derek
 */
public enum MarkdownHTMLRenderer implements FieldRenderer{
    INSTANCE;
    private PegDownProcessor pegDownProcessor;

    public String getRenderedString(String rawString) {
        if (pegDownProcessor == null) {
            pegDownProcessor = new PegDownProcessor();
        }
        return pegDownProcessor.markdownToHtml(rawString);
    }

    public static void main(String[] args) {
        String s = INSTANCE.getRenderedString("" +
                "## Hello World\n\n" +
                "* Hi this is some text\n" +
                "* Hi, http://google.com/\n");
        System.out.println(s);
    }
}
