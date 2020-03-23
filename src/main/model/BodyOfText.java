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
    //EFFECTS: changes current body of text to new given text
    @Override
    public void setText(String newText) {
        textBody = newText;
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

    @Override
    public String toString() {
        String[] arr = getText().split(" ", 4);

        if (arr.length < 4) {
            return getText();
        }
        return arr[0] + " " + arr[1] + " " + arr[2];
    }

}
