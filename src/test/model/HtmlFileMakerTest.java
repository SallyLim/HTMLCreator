package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlFileMakerTest {
    HtmlFileMaker htmlFileMaker;
    WebPage testPage;

    @BeforeEach
    void runBefore() {
        testPage = new WebPage();
        htmlFileMaker = new HtmlFileMaker(testPage);

    }

    @Test
    void testConstructor() {
        String html = htmlFileMaker.getHtml();
        String setHtml = testPage.returnHtml();

        assertEquals(setHtml, html);
    }

    @Test
    void testHtmlToFile() throws Exception {
        htmlFileMaker.htmlToFile(htmlFileMaker.getHtml());
        String fileToString = htmlFileMaker.getFileAsString();
        String setHtml = testPage.returnHtml();

        assertEquals(setHtml, fileToString);
    }
}
