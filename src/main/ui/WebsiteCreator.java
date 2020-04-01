package ui;

import model.*;
import persistence.HtmlFileMaker;
import persistence.LoadPage;
import persistence.SavePage;

import java.io.IOException;

//Does background work of the ui
public class WebsiteCreator {
    private WebPage website;

    //EFFECTS: runs the website creator app
    public WebsiteCreator() {
        loadPage();
    }

    //MODIFIES: file at PAGELOCATION
    //EFFECTS: saves current version of page to PAGELOCATION
    public void savePage() throws IOException {
        SavePage save = new SavePage();
        String json = save.makeJson(website);
        save.toFile(json);
    }

    //MODIFIES: this
    //EFFECTS: loads web page from PAGELOCATION; if no page is found, create new default web page
    public void loadPage() {
        LoadPage load = new LoadPage();

        try {
            String json = load.fromFileToJson();
            website = load.fromJsonToPage(json);
        } catch (IOException e) {
            website = new WebPage();
        }
    }


    //MODIFIES: file at HTMLLOCATION
    //EFFECTS: returns the html file version of web page
    public void returnHtmlFile() throws IOException {
        HtmlFileMaker htmlFileMaker = new HtmlFileMaker(website);
        String html = htmlFileMaker.getHtml();
        htmlFileMaker.htmlToFile(html);
    }

    public WebPage getWebsite() {
        return website;
    }

}
