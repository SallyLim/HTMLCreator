package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyOfTextTest {
    BodyOfText textA;
    BodyOfText textB;

    @BeforeEach
    void runBefore() {
        textA = new BodyOfText();
        textB = new BodyOfText();
    }

    @Test
    void testConstructor() {
        assertEquals("Sample Text", textA.getText());
        assertEquals(20, textA.getFontSize());
    }

    @Test
    void testSetText(){
        textA.setText("New Text");
        textB.setText("I love you a lot.");

        assertEquals("New Text", textA.getText());
        assertEquals("New Text", textA.toString());
        assertEquals("I love you", textB.toString());
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
