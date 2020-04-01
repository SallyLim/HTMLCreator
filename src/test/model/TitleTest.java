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
        assertEquals("blue", title.getBanner().getBannerColor());
        assertEquals("Title", title.toString());
    }

    @Test
    void testSetText() {
        title.setText("New Title");

        assertEquals("New Title", title.getText());
    }

    @Test
    void testSetFont() {
        title.setFont(60);

        assertEquals(60, title.getFontSize());
    }

    @Test
    void testReturnStringElementHtml() {
        String html = "<h1 style=\"background: blue; font-size: 46px\">Sample Title</h1>";

        assertEquals(html, title.returnStringElementHtml());
    }

}
