package ui;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StringElements;

import java.util.Optional;

//Processes the user input
public class Controller extends Application {
    private WebsiteCreator websiteCreator = new WebsiteCreator();
    private Editor editor = new Editor(this);
    private Configure configure;

    @FXML
    public SwingNode htmlViewer;

    @FXML
    public TreeView<String> currentLayoutList;

    @FXML
    public TreeView<String> editControlBar;

    @FXML
    public Button quitButton;

    @FXML
    public Button saveButton;

    //EFFECTS: configures the scene and elements on the scene
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller.class.getResource("View.fxml"));
        loader.setController(this);

        Parent root = loader.load();

        configure = new Configure(this);
        configure.createSwingContent(htmlViewer);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Website Creator");
        primaryStage.show();
    }

    public WebsiteCreator getWebsiteCreator() {
        return websiteCreator;
    }



    //EFFECTS: handles MouseEvents for the Layout list menu on the left
    public void addEventHandlerLayoutBar(MouseEvent event) {
        TreeItem<String> item = currentLayoutList.getSelectionModel().getSelectedItem();

        if (item.getValue().equals("Title")) {
            configure.getTitleEditChoices("Title", 0);
        } else {
            for (StringElements bodyOfTexts : websiteCreator.getWebsite().returnElementsOnPage()) {
                if (item.getValue().equals(bodyOfTexts.toString())) {
                    int index = websiteCreator.getWebsite().returnElementsOnPage().indexOf(bodyOfTexts);
                    configure.getEditChoices("Text", index);
                }
            }
        }
    }


    public void processTitleEdits(Optional<String> result, String type, int index) {
        if (result.isPresent()) {
            switch (result.get()) {
                case "Edit text":
                    editor.editText(type, index);
                    configure.createSwingContent(htmlViewer);
                    break;
                case "Change font":
                    editor.changeFont(type, index);
                    configure.createSwingContent(htmlViewer);
                    break;
            }

        }
    }


    public void processTextEdit(Optional<String> result, String type, int index) {
        if (result.isPresent()) {
            switch (result.get()) {
                case "Edit text":
                    editor.editText(type, index);
                    configure.configureLayoutList();
                    configure.createSwingContent(htmlViewer);
                    break;
                case "Delete text":
                    editor.deleteText(index);
                    configure.configureLayoutList();
                    configure.createSwingContent(htmlViewer);
                    break;
                case "Change font":
                    editor.changeFont(type, index);
                    configure.createSwingContent(htmlViewer);
                    break;
            }

        }
    }


    //EFFECTS: handles MouseEvents to the edit control bar
    public void addEventHandlerControlBar(MouseEvent event, TreeItem bodyItem) {
        TreeItem<String> item = editControlBar.getSelectionModel().getSelectedItem();

        switch (item.getValue()) {
            case "Edit banner color":
                editor.editBannerColor();
                configure.createSwingContent(htmlViewer);
                break;
            case "Add new body of text":
                editor.addText(bodyItem);
                configure.createSwingContent(htmlViewer);
                break;
        }
    }




}
