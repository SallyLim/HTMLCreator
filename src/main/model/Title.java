package model;

//Title for the web page which implements the StringElements interface
public class Title implements StringElements {
    private String title;
    private int fontSize;
    private Banner banner;

    //EFFECTS: has title, font size, and banner which are all set to default states
    public Title() {
        this.title = "Sample Title";
        this.fontSize = 46; //in px
        this.banner = new Banner();
    }

    //MODIFIES: this
    //EFFECTS: changes current title to new given title
    @Override
    public void setText(String newTitle) {
        title = newTitle;
    }

    //MODIFIES: this
    //EFFECTS: changes the current font size to the size specified
    @Override
    public void setFont(int textSize) {
        this.fontSize = textSize;
    }

    //EFFECTS: returns current font size
    @Override
    public int getFontSize() {
        return fontSize;
    }

    //EFFECTS: return current title
    @Override
    public String getText() {
        return this.title;
    }

    //EFFECTS: returns a string of the html version of the title with the banner
    @Override
    public String returnStringElementHtml() {
        String html = "";

        html = html.concat("<h1 style=\"color:" + banner.getBannerColor()
        + ";font-size:" + fontSize + "px;\">" + this.title + "</h1>");

        return html;
    }

    //EFFECTS: returns banner color
    public String getBanner() {
        return banner.getBannerColor();
    }

    //MODIFIES: this
    //EFFECTS: changes banner color
    public void changeBanner(String differentColor) {
        this.banner.changeBannerColor(differentColor.toLowerCase());
    }

}
