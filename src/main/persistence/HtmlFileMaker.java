package persistence;

import model.WebPage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlFileMaker {
    public static String HTMLLOCATION = "./data/html.html";
    public String html;

    //EFFECTS: constructs saver that converts web page data to a file
    public HtmlFileMaker(WebPage webPage) {
        html = webPage.returnHtml();
    }

    //MODIFIES: file at HTMLLOCATION
    //EFFECTS: converts html string to a file at HTMLLOCATION
    public void htmlToFile(String html) throws IOException {
        FileWriter file = new FileWriter(HTMLLOCATION);

        PrintWriter printer = new PrintWriter(file);
        printer.print(html);
        printer.close();
    }

    //EFFECTS: returns html string in file
    public String getHtml() {
        return html;
    }

    //EFFECTS: gets saved file as string
    public String getFileAsString() throws Exception {
        String fileAsString;
        fileAsString = new String(Files.readAllBytes(Paths.get(HTMLLOCATION)));
        return fileAsString;
    }
}
