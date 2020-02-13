package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyOfTextTest {
    BodyOfText textA;
    //BodyOfText textB;

    @BeforeEach
    void runBefore() {
        textA = new BodyOfText();
        //textB = new BodyOfText();
    }

    @Test
    void testConstructor() {
        assertEquals("Sample Text", textA.getText());
        assertEquals(20, textA.getFontSize());
    }

    @Test
    void testConcatText() {
        textA.concatText(" Default");

        assertEquals("Sample Text Default", textA.getText());
    }

    @Test
    void testDeleteText() {
        textA.deleteText("Text");

        assertEquals("Sample ", textA.getText());
    }


    @Test
    void testSetFont() {
        textA.setFont(40);

        assertEquals(40, textA.getFontSize());
    }

    @Test
    void testReturnStringElementHtml() {
        String html = "<P style=\"font-size:20px;\">Sample Text</P>";

        assertEquals(html, textA.returnStringElementHtml());
    }
}
