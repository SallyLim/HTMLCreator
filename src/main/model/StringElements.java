package model;

//Interface for all text related elements of the web page
public interface StringElements {

    void setText(String newText);

    void setFont(int textSize);

    int getFontSize();

    String getText();

    String returnStringElementHtml();

}
