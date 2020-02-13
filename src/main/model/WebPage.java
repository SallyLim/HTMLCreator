package model;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;

import java.util.ArrayList;


//Puts together all of the elements of the page
public class WebPage {
    private ArrayList<StringElements> elementsOnPage;
    private ArrayList<String> elementsDescription;
    private Title title;

    //EFFECTS: keeps track of the elements currently on the page
    //         when initialized, automatically creates a title element on page
    public WebPage() {
        this.elementsOnPage = new ArrayList<>();
        this.elementsDescription = new ArrayList<>();
        this.title = new Title();
        elementsOnPage.add(title);
        elementsDescription.add("Title");
    }

    //MODIFIES: this
    //EFFECTS: add BodyOfText element
    public void addTextBubble() {
        elementsOnPage.add(new BodyOfText());
    }

    //MODIFIES: this
    //EFFECTS: adds custom description to the newly added BodyOfText element
    public void addTextDescription(String description) {
        elementsDescription.add(description);
    }

    //EFFECTS: gets description of element given its order on the page
    public String getDescription(int order) {
        int index = order - 1;

        return elementsDescription.get(index);
    }

    //EFFECTS: gets description of element given the specific object
    public String getTextDescriptionOfElement(StringElements text) {

        int indexOfElement = elementsOnPage.indexOf(text);
        int indexOfDescription = 0;

        for (StringElements element : elementsOnPage) {
            if (element.equals(text)) {
                indexOfDescription = elementsOnPage.indexOf(text);
                break;
            }
        }
        return elementsDescription.get(indexOfDescription);
    }

    //EFFECTS: gets object given the description of element
    public StringElements getTextBubble(String description) {
        int indexOfElement = 0;

        for (String elementDescription : elementsDescription) {
            if (elementDescription.equalsIgnoreCase(description)) {
                indexOfElement = elementsDescription.indexOf(description);
                break;
            }
        }
        return elementsOnPage.get(indexOfElement);
    }

    //EFFECTS: returns the number of elements of page
    public int getLengthOfElements() {
        return elementsOnPage.size();
    }

    //EFFECTS: returns the number of element descriptions
    public int getLengthOfDescription() {
        return elementsDescription.size();
    }

    //MODIFIES: this
    //REQUIRES: elementsDescription and elementsOnPage are not empty
    //EFFECTS: removes the object from page given its description
    public void removeTextBubble(String description) {
        int indexOfElement = 0;

        for (String elementDescription : elementsDescription) {
            if (elementDescription.equalsIgnoreCase(description)) {
                indexOfElement = elementsDescription.indexOf(description);
                elementsDescription.remove(description);
                elementsOnPage.remove(indexOfElement);
                break;
            }
        }
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
