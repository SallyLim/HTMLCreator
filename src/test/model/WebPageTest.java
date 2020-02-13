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

        assertEquals(1, page.getLengthOfElements());
        assertEquals(1, page.getLengthOfDescription());
        assertEquals("Title", page.getDescription(1));
        assertEquals("Sample Title", expectedTitle);

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
    void testGetTextBoxExists() {
        StringElements titleSearched = page.getTextBubble("Title");

        assertEquals(page.getTitle(), titleSearched);
    }

    @Test
    void testGetTextBoxNotExists() {
        page.addTextBubble();
        page.addTextDescription("Text Box 1");

        StringElements text = page.getTextBubble("Text Box 2");

        assertEquals(null, text);
    }

    @Test
    void testRemoveTextBubbleNoException() throws NoElementException {
        page.addTextBubble();
        page.addTextDescription("test text");

        page.removeTextBubble("test text");

        assertEquals(1, page.getLengthOfDescription());
        assertEquals(1, page.getLengthOfElements());
    }

    @Test
    void testRemoveTextBubbleException() throws NoElementException {
        page.addTextBubble();
        page.addTextDescription("test text");

        boolean thrown = false;

        try {
            page.removeTextBubble("test");
        } catch (NoElementException n) {
            thrown = true;
        }

        assertTrue(thrown);
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
