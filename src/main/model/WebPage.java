package model;

import java.util.ArrayList;


//Puts together all of the elements of the page
public class WebPage {
    private ArrayList<StringElements> elementsOnPage;

    //EFFECTS: keeps track of the elements currently on the page
    //         when initialized, automatically creates a title element on page
    public WebPage() {
        this.elementsOnPage = new ArrayList<>();
        elementsOnPage.add(new Title());
    }

    //MODIFIES: this
    //EFFECTS: removes text element
    public void removeText(int index) {
        elementsOnPage.remove(index + 1);
    }

    //EFFECTS: returns elementsOnPage array list
    public ArrayList<StringElements> returnElementsOnPage() {
        ArrayList<StringElements> listOfBodyOfText = new ArrayList<>();

        for (int i = 1; i < elementsOnPage.size(); i++) {
            listOfBodyOfText.add(elementsOnPage.get(i));
        }

        return listOfBodyOfText;
    }

    //MODIFIES: this
    //EFFECTS: add BodyOfText element
    public BodyOfText addTextBubble() {
        BodyOfText newBody = new BodyOfText();
        elementsOnPage.add(newBody);
        return newBody;
    }

    //REQUIRES: title is in elementsOnPage list
    //EFFECTS: get title object of page
    public Title getTitle() {
        return (Title) elementsOnPage.get(0);
    }


    //EFFECTS: returns a string of the html version of the web page
    public String returnHtml() {
        String html = "<!DOCTYPE html>\n" + "<html>\n";

        for (StringElements element : elementsOnPage) {
            html = html.concat(element.returnStringElementHtml() + "\n");
        }

        return html;
    }

}
