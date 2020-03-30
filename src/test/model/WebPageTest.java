package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Tests for methods of WebPage class
public class WebPageTest {
    WebPage page;

    @BeforeEach
    void runBefore() {
        page = new WebPage();
    }

    @Test
    void testConstructor() {
        String expectedTitle = page.getTitle().getText();

        assertEquals("Sample Title", expectedTitle);
    }

    @Test
    void testReturnHtmlBasic() {
        String html = "<!DOCTYPE html>\n" + "<html>\n" +
        "<h1 style=\"background: blue; font-size: 46px\">Sample Title</h1>\n";

        assertEquals(html, page.returnHtml());
    }

    @Test
    void testReturnHtmlWithBody() {
        page.addTextBubble();

        String html = "<!DOCTYPE html>\n" + "<html>\n" +
                "<h1 style=\"background: blue; font-size: 46px\">Sample Title</h1>\n"
                + "<P style=\"font-size:20px;\">Sample Text</P>\n";

        assertEquals(html, page.returnHtml());
    }

}
