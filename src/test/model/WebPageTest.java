package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests for methods of WebPage class
public class WebPageTest {
    WebPage page;

    @BeforeEach
    void runBefore() {
        page = new WebPage();
    }

    @Test
    void testConstructor() {
        assertEquals(1, page.getLengthOfElements());
        assertEquals(1, page.getLengthOfDescription());
        assertEquals("Title", page.getDescription(1));
    }

    @Test
    void testAddTextBubbleAndDescription() {
        page.addTextBubble();
        page.addTextDescription("Text Box 1");

        assertEquals(2, page.getLengthOfElements());
        assertEquals(2, page.getLengthOfDescription());
        assertEquals("Text Box 1", page.getDescription(2));
    }

    @Test
    void testRemoveTextBubble() {
        page.addTextBubble();
        page.addTextDescription("test text");

        page.removeTextBubble("test text");

        assertEquals(1, page.getLengthOfDescription());
        assertEquals(1, page.getLengthOfElements());
    }

    @Test
    void testReturnHtmlBasic() {
        String html = "<!DOCTYPE html>\n" + "<html>\n" +
        "<h1 style=\"color:lavender;font-size:46px;\">Sample Title</h1>\n";

        assertEquals(html, page.returnHtml());
    }

    @Test
    void testReturnHtmlWithBody() {
        page.addTextBubble();
        page.addTextDescription("test text");

        String html = "<!DOCTYPE html>\n" + "<html>\n" +
                "<h1 style=\"color:lavender;font-size:46px;\">Sample Title</h1>\n"
                + "<P style=\"font-size:20px;\">Sample Text</P>\n";

        assertEquals(html, page.returnHtml());
    }

}
