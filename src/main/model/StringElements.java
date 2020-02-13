package model;

//Interface for all text related elements of the web page
public interface StringElements {

    void concatText(String addedText);

    void deleteText(String deletedText);

    void setFont(int textSize);

    int getFontSize();

    String getText();

    String returnStringElementHtml();

}
