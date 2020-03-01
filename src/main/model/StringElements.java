package model;

//Interface for all text related elements of the web page
public interface StringElements {

    //MODIFIES: this
    //EFFECTS: changes current text to new given text
    void setText(String newText);

    //MODIFIES: this
    //EFFECTS: changes the current font size to the size specified
    void setFont(int textSize);

    int getFontSize();

    String getText();

    //EFFECTS: returns a string of the html version of the text
    String returnStringElementHtml();

}
