package persistence;


import com.google.gson.JsonParseException;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.WebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
    void testMakeAndGetJson(){
        String savedJson = saving.makeJson(testPage);
        String loadedJson = "";

        try {
            saving.toFile(savedJson);
            loadedJson = loading.fromFileToJson();
        } catch (IOException e) {
            fail("failed to open and save file.");
        }

        assertEquals(savedJson, loadedJson);
    }

    @Test
    void testToAndFromFile() {
        String savedJson = saving.makeJson(testPage);
        String savedFile = "";
        String loadedString = "";

        try {
            saving.toFile(savedJson);
            savedFile = saving.getFileAsString();
            loadedString = loading.fromFileToJson();
        } catch (Exception e) {
            fail("failed to open and save file.");
        }

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
    void testSamePage() {
        String savedJson = saving.makeJson(testPage);
        String loadedJson = "";

        try {
            saving.toFile(savedJson);
            loadedJson = loading.fromFileToJson();
        } catch (IOException e) {
            fail("failed to open and save file.");

        }
        WebPage loadedPage = loading.fromJsonToPage(loadedJson);

        assertEquals(testPage.returnHtml(), loadedPage.returnHtml());
    }

}
