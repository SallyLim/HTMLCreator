package model;

//The solid color banner underneath the title
public class Banner {
    public static int WIDTH = 100;
    //has to be a integer between [0, 100] because it represents %
    public static int HEIGHT = 470;

    private String color;

    //EFFECTS: has a color and it is set to a default color when initiated
    public Banner() {
        this.color = "lavender";
    }

    //EFFECTS: returns the current color of banner
    public String getBannerColor() {
        return this.color;
    }

    //MODIFIES: this
    //EFFECTS: changes the banner color to the new specified color
    public void changeBannerColor(String newColor) {
        this.color = newColor;
    }
}
