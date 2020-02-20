package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.StringElements;
import model.WebPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static persistence.SavePage.PAGELOCATION;

//loads current state of website from file
public class LoadPage {
    private static Gson gson;

    //TODO: comments
    public LoadPage() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(StringElements.class, new ElementInterfaceAdapter());

        gson = builder.create();
    }

    public String fromFileToJson() throws IOException {
        String fileAsString;
        fileAsString = new String(Files.readAllBytes(Paths.get(PAGELOCATION)));
        return fileAsString;
    }

    public WebPage fromJsonToPage(String json) {
        return gson.fromJson(json, WebPage.class);
    }

    //TODO: any other getters or setters?

}
