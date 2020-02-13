package model;

//The body of text that can be added to the page and implements StringElements interface
public class BodyOfText implements StringElements {
    private String textBody;
    private int fontSize;

    //EFFECTS: has text and font size that are set to default values when initiated
    public BodyOfText() {
        this.textBody = "Sample Text";
        this.fontSize = 20; //in px
    }

    //MODIFIES: this
    //EFFECTS: adds new string of text to original string of text
    @Override
    public void concatText(String addedText) {
        this.textBody = this.textBody.concat(addedText);
    }

    //MODIFIES: this
    //REQUIRES: deletedText.length() <= textBody.length() and deletedText characters must be the same characters
    //          as the textBody.substring of textBody.length() - deletingText.length() to the end of textBody
    //EFFECTS: deletes texts starting from right to left like a backspace button
    @Override
    public void deleteText(String deletedText) {
        int deletingTextLength = deletedText.length();
        int endIndexOfNewText = this.textBody.length() - deletingTextLength;

        this.textBody = this.textBody.substring(0, endIndexOfNewText);
    }

    //MODIFIES: this
    //EFFECTS: changes font size
    @Override
    public void setFont(int textSize) {
        this.fontSize = textSize;
    }

    //EFFECTS: returns current font size
    @Override
    public int getFontSize() {
        return fontSize;
    }

    //EFFECTS: returns current text
    @Override
    public String getText() {
        return this.textBody;
    }

    //EFFECTS: returns a string of the html version of the body of text
    @Override
    public String returnStringElementHtml() {
        String html = "";

        html = html.concat("<P style=\"font-size:" + fontSize
                + "px;\">" + textBody + "</P>");

        return html;
    }

}
