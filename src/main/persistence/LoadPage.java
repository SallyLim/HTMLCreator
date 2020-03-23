package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.StringElements;
import model.WebPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static persistence.SavePage.PAGELOCATION;

//loads current state of website from file
public class LoadPage {
    private static Gson gson;

    //EFFECTS: constructs a loader that converts a file back to a web page
    public LoadPage() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(StringElements.class, new ElementInterfaceAdapter());

        gson = builder.create();
    }

    //EFFECTS: converts file to a Json string
    public String fromFileToJson() throws IOException {
        String fileAsString;
        fileAsString = new String(Files.readAllBytes(Paths.get(PAGELOCATION)));
        return fileAsString;
    }

    //EFFECTS: converts Json string to a web page
    public WebPage fromJsonToPage(String json) {
        return gson.fromJson(json, WebPage.class);
    }

}
