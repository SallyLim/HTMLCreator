package persistence;


import com.google.gson.JsonParseException;
import model.NoElementException;
import model.WebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//tests making and getting json and to and from file of SavePage and LoadPage by comparing if they are the same
public class SaveAndLoadTests {
    private SavePage saving;
    private WebPage testPage;
    private LoadPage loading;

    @BeforeEach
    void runBefore() {
        saving = new SavePage();
        testPage = new WebPage();
        loading = new LoadPage();
    }

    @Test
    void testMakeAndGetJson() throws IOException {
        String savedJson = saving.makeJson(testPage);
        saving.toFile(savedJson);
        String loadedJson = loading.fromFileToJson();

        assertEquals(savedJson, loadedJson);
    }

    @Test
    void testToAndFromFile() throws Exception {
        String savedJson = saving.makeJson(testPage);
        saving.toFile(savedJson);
        String savedFile = saving.getFileAsString();
        String loadedString = loading.fromFileToJson();

        assertEquals(loadedString, savedFile);
    }

    @Test
    void testToAndFromFileClassNotFoundException() {
        String testJson = "{\n" +
                "  \"elementsOnPage\": [\n" +
                "    {\n" +
                "      \"Class\": \"model.Test\",\n" +
                "      \"Description\": {\n" +
                "        \"title\": \"Sample Title\",\n" +
                "        \"fontSize\": 46,\n" +
                "        \"banner\": {\n" +
                "          \"color\": \"lavender\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"elementsDescription\": [\n" +
                "    \"Title\"\n" +
                "  ],\n" +
                "  \"title\": {\n" +
                "    \"title\": \"Sample Title\",\n" +
                "    \"fontSize\": 46,\n" +
                "    \"banner\": {\n" +
                "      \"color\": \"lavender\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        boolean thrown = false;

        try{
            loading.fromJsonToPage(testJson);
        } catch (JsonParseException e) {
            thrown = true;
    }

        assertTrue(thrown);

    }

    @Test
    void testSamePage() throws IOException {
        String savedJson = saving.makeJson(testPage);
        saving.toFile(savedJson);
        String loadedJson = loading.fromFileToJson();
        WebPage loadedPage = loading.fromJsonToPage(loadedJson);


        assertEquals(testPage.getDescription(1), loadedPage.getDescription(1));
        assertEquals(testPage.getLengthOfElements(), loadedPage.getLengthOfElements());
        assertEquals(testPage.returnHtml(), loadedPage.returnHtml());
    }

}
