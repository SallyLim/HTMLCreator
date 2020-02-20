package persistence;


import model.WebPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    //TODO: fix
    @Test
    void testSamePage() throws IOException {
        String savedJson = saving.makeJson(testPage);
        saving.toFile(savedJson);
        String loadedJson = loading.fromFileToJson();
        WebPage loadedPage = loading.fromJsonToPage(loadedJson);

        assertEquals(testPage, loadedPage);
    }

}
