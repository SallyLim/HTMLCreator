package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BodyOfText;
import model.StringElements;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//Processes the user input
public class Controller extends Application {
    private WebsiteCreator websiteCreator = new WebsiteCreator();

    @FXML
    public SwingNode htmlViewer;

    @FXML
    public Button quitButton;

    @FXML
    public Button saveButton;

    @FXML
    public TreeView<String> currentLayoutList;

    @FXML
    public TreeView<String> editControlBar;

    //EFFECTS: configures the scene and elements on the scene
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller.class.getResource("View.fxml"));
        loader.setController(this);

        Parent root = loader.load();

        createSwingContent(htmlViewer);

        configureQuitButton();
        configureSaveButton();
        configureLayoutList();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Website Creator");
        primaryStage.show();
    }

    //EFFECTS: configures the layout list on the left
    private void configureLayoutList() {
        TreeItem rootItem = new TreeItem("Current Layout:");

        TreeItem titleItem = new TreeItem("Header");
        titleItem.getChildren().add(new TreeItem(websiteCreator.website.getTitle().toString()));
        rootItem.getChildren().add(titleItem);

        TreeItem bodyItem = new TreeItem("Body of text");

        for (StringElements bodyOfTexts : websiteCreator.website.returnElementsOnPage()) {
            bodyItem.getChildren().add(new TreeItem(bodyOfTexts.toString()));
        }

        rootItem.getChildren().add(bodyItem);

        currentLayoutList.setRoot(rootItem);
        currentLayoutList.setShowRoot(false);

        configureControlBar(bodyItem);

        EventHandler<MouseEvent> mouseEventEventHandler = (MouseEvent event) -> {
            addEventHandlerLayoutBar(event);
        };

        currentLayoutList.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
    }

    //EFFECTS: handles MouseEvents for the Layout list menu on the left
    private void addEventHandlerLayoutBar(MouseEvent event) {
        TreeItem<String> item = currentLayoutList.getSelectionModel().getSelectedItem();

        if (item.getValue().equals("Title")) {
            getTitleEditChoices("Title", 0);
        } else {
            for (StringElements bodyOfTexts : websiteCreator.website.returnElementsOnPage()) {
                if (item.getValue().equals(bodyOfTexts.toString())) {
                    int index = websiteCreator.website.returnElementsOnPage().indexOf(bodyOfTexts);
                    getEditChoices("Text", index);
                }
            }
        }
    }

    //EFFECTS: when an title element on the layout list is selected, return a list of edits you can do
    private void getTitleEditChoices(String type, int index) {
        String[] choices = {"Edit text", "Change font"};
        List<String> dialogData = Arrays.asList(choices);
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Edit text", dialogData);

        dialog.setTitle("Edit options");
        dialog.setHeaderText("Select a text edit option:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            switch (result.get()) {
                case "Edit text":
                    editText(type, index);
                    createSwingContent(htmlViewer);
                    break;
                case "Change font":
                    changeFont(type, index);
                    createSwingContent(htmlViewer);
                    break;
            }

        }
    }

    //EFFECTS: when an text element on the layout list is selected, return a list of edits you can do
    private void getEditChoices(String type, int index) {
        String[] choices = {"Edit text", "Delete text", "Change font"};
        List<String> dialogData = Arrays.asList(choices);
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Edit text", dialogData);

        dialog.setTitle("Edit options");
        dialog.setHeaderText("Select a text edit option:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            switch (result.get()) {
                case "Edit text":
                    editText(type, index);
                    createSwingContent(htmlViewer);
                    break;
                case "Delete text":
                    deleteText(index);
                    createSwingContent(htmlViewer);
                    break;
                case "Change font":
                    changeFont(type, index);
                    createSwingContent(htmlViewer);
                    break;
            }

        }
    }

    //MODIFIES: StringElement's font size
    //EFFECTS: changes appropriate element's font size
    private void changeFont(String type, int index) {
        Dialog<String> dialog = new TextInputDialog("Enter an integer value...");
        dialog.setTitle("Set new font size");
        int currentSize = 0;
        if (type.equals("Title")) {
            currentSize = websiteCreator.website.getTitle().getFontSize();
        } else {
            currentSize = websiteCreator.website.returnElementsOnPage().get(index).getFontSize();
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
    private void setFontHandleException(String type, String entered, int index) {
        try {
            if (type.equals("Title")) {
                websiteCreator.website.getTitle().setFont(Integer.parseInt(entered));
            } else {
                websiteCreator.website.returnElementsOnPage().get(index).setFont(Integer.parseInt(entered));
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid integer entered.\n Please enter a valid integer.");
            alert.showAndWait();
        }
    }

    //MODIFIES: layout list, elementsOnPage
    //EFFECTS: deletes a selected body of text
    private void deleteText(int index) {
        websiteCreator.website.removeText(index);
        configureLayoutList();
    }

    //MODIFIES: BodyOfText's text
    //EFFECTS: edits the text of the selected, existing body of text
    private void editText(String type, int index) {
        Dialog dialog = new TextInputDialog("Enter new text");
        dialog.setTitle("Text editor");
        dialog.setHeaderText("Enter new text to replace existing text");

        Optional<String> result = dialog.showAndWait();
        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        if (type.equals("Title")) {
            websiteCreator.website.getTitle().setText(entered);
        } else {
            websiteCreator.website.returnElementsOnPage().get(index).setText(entered);
        }

        configureLayoutList();
    }

    //EFFECTS: creates a edit control bar on the right side to handle other edits to elements
    private void configureControlBar(TreeItem body) {
        TreeItem rootItem = new TreeItem("Edit Menu");

        TreeItem titleItem = new TreeItem("TITLE edits");
        titleItem.getChildren().add(new TreeItem("Edit banner color"));
        rootItem.getChildren().add(titleItem);

        TreeItem bodyItem = new TreeItem("BODY OF TEXT edits");
        bodyItem.getChildren().add(new TreeItem("Add new body of text"));
        rootItem.getChildren().add(bodyItem);

        editControlBar.setRoot(rootItem);
        editControlBar.setShowRoot(false);

        EventHandler<MouseEvent> mouseEventEventHandler = (MouseEvent event) -> {
            addEventHandlerControlBar(event, body);
        };

        editControlBar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
    }

    //EFFECTS: handles MouseEvents to the edit control bar
    private void addEventHandlerControlBar(MouseEvent event, TreeItem bodyItem) {
        TreeItem<String> item = editControlBar.getSelectionModel().getSelectedItem();

        switch (item.getValue()) {
            case "Edit banner color":
                editBannerColor();
                createSwingContent(htmlViewer);
                break;
            case "Add new body of text":
                addText(bodyItem);
                createSwingContent(htmlViewer);
                break;
        }
    }

    //MODIFIES: layout list, elementsOnPage
    //EFFECTS: adds a new body of text
    private void addText(TreeItem bodyItem) {
        BodyOfText newBody = websiteCreator.website.addTextBubble();
        bodyItem.getChildren().add(new TreeItem(newBody.toString()));
    }

    //MODIFIES: Banner's color
    //EFFECTS: changes the title banner color with red, blue, or orange
    private void editBannerColor() {
        Dialog<String> dialog = new TextInputDialog("Enter a color name...");
        dialog.setTitle("Set new banner color");
        String currentColor = websiteCreator.website.getTitle().getBanner();
        dialog.setHeaderText("The current color is " + currentColor + ".\n"
                + "Enter a new color from the choices below:\n" + "- Red\n- Blue\n- Orange\n");
        Optional<String> result = dialog.showAndWait();
        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        websiteCreator.website.getTitle().changeBanner(entered);
    }


    //EFFECTS: creates a real time window to display the html file
    private void createSwingContent(final SwingNode htmlViewer) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JEditorPane editorPane = new JEditorPane();
                editorPane.setEditable(false);

                JScrollPane jscrollPane = new JScrollPane(editorPane) {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(700,550);
                    }
                };

                HTMLEditorKit kit = new HTMLEditorKit();
                editorPane.setEditorKit(kit);

                Document doc = kit.createDefaultDocument();
                editorPane.setDocument(doc);
                editorPane.setText(websiteCreator.website.returnHtml());

                htmlViewer.setContent(jscrollPane);
            }
        });
    }

    //MODIFIES: file at html.html
    //EFFECTS: when user clicks "quit" button, save html file and ask to save
    private void configureQuitButton() {
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    websiteCreator.returnHtmlFile();
                    askToSave();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setContentText("Program has saved progress successfully. Program will quit.");
                    alert.showAndWait();
                    Platform.exit();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("An error occurred while saving your HTML file. Please try again.");
                    alert.showAndWait();
                }
            }
        });
    }

    //MODIFIES: file at page.txt
    //EFFECTS: saves page when user clicks "save" button
    private void configureSaveButton() {
        saveButton.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    websiteCreator.savePage();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setContentText("Program has saved progress successfully.");
                    alert.showAndWait();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("An error occurred while saving your page. Please try again.");
                    alert.showAndWait();
                }
            }
        }));
    }

    //MODIFIES: file at page.txt
    //EFFECTS: asks user if want to save before quitting
    private void askToSave() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Saving File Confirmation");
        confirmation.setContentText("Would you like to save file before quitting?");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                websiteCreator.savePage();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("An error occurred while saving your page. Please try again.");
                alert.showAndWait();
            }
        }
    }


}
