package model;

//responsible for the creation of the string aspects of elements
public interface StringCreator {
    //EFFECTS: returns a string of the html version of the title with the banner
    String returnStringElementHtml();

    @Override
    String toString();
}
