package persistence;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.StringElements;
import model.WebPage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

//Saves current state of elements of web page
public class SavePage {
    public static String PAGELOCATION = "./data/page.txt";
    private static Gson gson;

    //EFFECTS: constructs saver that converts web page data to a file
    public SavePage() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(StringElements.class, new ElementInterfaceAdapter());

        gson = builder.create();
    }

    //EFFECTS: makes Json String from web page data
    public String makeJson(WebPage page) {
        return gson.toJson(page);
    }

    //MODIFIES: file at PAGELOCATION
    //EFFECTS: converts Json String to a file at PAGELOCATION
    public void toFile(String json) throws IOException {
        FileWriter file = new FileWriter(PAGELOCATION);

        PrintWriter printer = new PrintWriter(file);
        printer.print(json);
        printer.close();
    }

    //EFFECTS: gets saved file as string
    public String getFileAsString() throws Exception {
        String fileAsString;
        fileAsString = new String(Files.readAllBytes(Paths.get(PAGELOCATION)));
        return fileAsString;
    }
}
