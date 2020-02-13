package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTest {
    Title title;

    @BeforeEach
    void runBefore() {
        title = new Title();
    }

    @Test
    void testConstructor() {
        assertEquals("Sample Title", title.getText());
        assertEquals(46, title.getFontSize());
        assertEquals("lavender", title.getBanner());
    }

    @Test
    void testConcatText() {
        title.concatText(" Test");

        assertEquals("Sample Title Test", title.getText());
    }

    @Test
    void testDeleteText() {
        title.deleteText("ample Title");

        assertEquals("S", title.getText());
    }

    @Test
    void testSetFont() {
        title.setFont(60);

        assertEquals(60, title.getFontSize());
    }

    @Test
    void testReturnStringElementHtml() {
        String html = "<h1 style=\"color:lavender;font-size:46px;\">Sample Title</h1>";

        assertEquals(html, title.returnStringElementHtml());
    }

    @Test
    void testChangeBanner() {
        title.changeBanner("blue");

        assertEquals("blue", title.getBanner());
    }
}
