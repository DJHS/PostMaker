package com.jeromecompsci.postmaker.core.fieldrenderers;

import com.jeromecompsci.postmaker.core.FieldRenderer;
import org.pegdown.PegDownProcessor;

/**
 * @author derek
 */
public class MarkdownHTMLRenderer implements FieldRenderer{
    private PegDownProcessor pegDownProcessor;

    public String getRenderedString(String rawString) {
        if (pegDownProcessor == null) {
            pegDownProcessor = new PegDownProcessor();
        }
        return pegDownProcessor.markdownToHtml(rawString);
    }

    public static void main(String[] args) {
        FieldRenderer r = new MarkdownHTMLRenderer();
        String s = r.getRenderedString("" +
                "## Hello World\n\n" +
                "* Hi this is some text\n" +
                "* Hi, http://google.com/\n");
        System.out.println(s);
    }
}
