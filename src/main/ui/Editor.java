package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import model.BodyOfText;

import java.util.Optional;

//processes the edits wanting to be done on the webpage
public class Editor {
    Controller controller;

    public Editor(Controller controller) {
        this.controller = controller;
    }

    //MODIFIES: layout list, elementsOnPage
    //EFFECTS: adds a new body of text
    public void addText(TreeItem bodyItem) {
        BodyOfText newBody = controller.getWebsiteCreator().getWebsite().addTextBubble();
        bodyItem.getChildren().add(new TreeItem(newBody.toString()));
    }


    //MODIFIES: layout list, elementsOnPage
    //EFFECTS: deletes a selected body of text
    public void deleteText(int index) {
        controller.getWebsiteCreator().getWebsite().removeText(index);
    }

    //MODIFIES: BodyOfText's text
    //EFFECTS: edits the text of the selected, existing body of text
    public void editText(String type, int index) {
        Dialog dialog = new TextInputDialog("Enter new text");
        dialog.setTitle("Text editor");
        dialog.setHeaderText("Enter new text to replace existing text");

        Optional<String> result = dialog.showAndWait();
        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        if (type.equals("Title")) {
            controller.getWebsiteCreator().getWebsite().getTitle().setText(entered);
        } else {
            controller.getWebsiteCreator().getWebsite().returnElementsOnPage().get(index).setText(entered);
        }

    }

    //MODIFIES: Banner's color
    //EFFECTS: changes the title banner color with red, blue, or orange
    public void editBannerColor() {
        Dialog<String> dialog = new TextInputDialog("Enter a color name...");
        dialog.setTitle("Set new banner color");
        String currentColor = controller.getWebsiteCreator().getWebsite().getTitle().getBanner().getBannerColor();
        dialog.setHeaderText("The current color is " + currentColor + ".\n"
                + "Enter a new color from the choices below:\n" + "- Red\n- Blue\n- Orange\n");
        Optional<String> result = dialog.showAndWait();
        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        controller.getWebsiteCreator().getWebsite().getTitle().getBanner().changeBannerColor(entered);
    }


    //MODIFIES: StringElement's font size
    //EFFECTS: changes appropriate element's font size
    public void changeFont(String type, int index) {
        Dialog<String> dialog = new TextInputDialog("Enter an integer value...");
        dialog.setTitle("Set new font size");
        int currentSize = 0;
        if (type.equals("Title")) {
            currentSize = controller.getWebsiteCreator().getWebsite().getTitle().getFontSize();
        } else {
            currentSize = controller.getWebsiteCreator().getWebsite().returnElementsOnPage().get(index).getFontSize();
        }
        dialog.setHeaderText("The current font size is " + currentSize + ".\n" + "Enter a new font size.\n");
        Optional<String> result = dialog.showAndWait();
        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        setFontHandleException(type, entered, index);
    }

    //EFFECTS: handles NumberFormatException of the parseInt from user input
    public void setFontHandleException(String type, String entered, int index) {
        try {
            if (type.equals("Title")) {
                controller.getWebsiteCreator().getWebsite().getTitle().setFont(Integer.parseInt(entered));
            } else {
                controller.getWebsiteCreator().getWebsite().returnElementsOnPage().get(index)
                        .setFont(Integer.parseInt(entered));
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid integer entered.\n Please enter a valid integer.");
            alert.showAndWait();
        }
    }


}
