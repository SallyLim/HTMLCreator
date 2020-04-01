package ui;

import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.StringElements;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Configure {
    private Controller controller;


    public Configure(Controller controller) {
        this.controller = controller;
        configureLayoutList();
        configureSaveButton();
        configureQuitButton();
    }

    //EFFECTS: configures the layout list on the left
    public void configureLayoutList() {
        TreeItem rootItem = new TreeItem("Current Layout:");

        TreeItem titleItem = new TreeItem("Header");
        titleItem.getChildren().add(new TreeItem(controller.getWebsiteCreator().getWebsite().getTitle().toString()));
        rootItem.getChildren().add(titleItem);

        TreeItem bodyItem = new TreeItem("Body of text");

        for (StringElements bodyOfTexts : controller.getWebsiteCreator().getWebsite().returnElementsOnPage()) {
            bodyItem.getChildren().add(new TreeItem(bodyOfTexts.toString()));
        }

        rootItem.getChildren().add(bodyItem);

        controller.currentLayoutList.setRoot(rootItem);
        controller.currentLayoutList.setShowRoot(false);

        configureControlBar(bodyItem);

        EventHandler<MouseEvent> mouseEventEventHandler = (MouseEvent event) -> {
            controller.addEventHandlerLayoutBar(event);
        };

        controller.currentLayoutList.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
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

        controller.editControlBar.setRoot(rootItem);
        controller.editControlBar.setShowRoot(false);

        EventHandler<MouseEvent> mouseEventEventHandler = (MouseEvent event) -> {
            controller.addEventHandlerControlBar(event, body);
        };

        controller.editControlBar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);
    }

    //MODIFIES: file at page.txt
    //EFFECTS: saves page when user clicks "save" button
    private void configureSaveButton() {
        controller.saveButton.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    controller.getWebsiteCreator().savePage();
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


    //MODIFIES: file at html.html
    //EFFECTS: when user clicks "quit" button, save html file and ask to save
    private void configureQuitButton() {
        controller.quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    controller.getWebsiteCreator().returnHtmlFile();
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
    //EFFECTS: asks user if want to save before quitting
    private void askToSave() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Saving File Confirmation");
        confirmation.setContentText("Would you like to save file before quitting?");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                controller.getWebsiteCreator().savePage();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("An error occurred while saving your page. Please try again.");
                alert.showAndWait();
            }
        }
    }


    //EFFECTS: creates a real time window to display the html file
    public void createSwingContent(final SwingNode htmlViewer) {
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
                editorPane.setText(controller.getWebsiteCreator().getWebsite().returnHtml());

                htmlViewer.setContent(jscrollPane);
            }
        });
    }

    //EFFECTS: when an title element on the layout list is selected, return a list of edits you can do
    public void getTitleEditChoices(String type, int index) {
        String[] choices = {"Edit text", "Change font"};
        List<String> dialogData = Arrays.asList(choices);
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Edit text", dialogData);

        dialog.setTitle("Edit options");
        dialog.setHeaderText("Select a text edit option:");

        Optional<String> result = dialog.showAndWait();
        controller.processTitleEdits(result, type, index);
    }

    //EFFECTS: when an text element on the layout list is selected, return a list of edits you can do
    public void getEditChoices(String type, int index) {
        String[] choices = {"Edit text", "Delete text", "Change font"};
        List<String> dialogData = Arrays.asList(choices);
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Edit text", dialogData);

        dialog.setTitle("Edit options");
        dialog.setHeaderText("Select a text edit option:");

        Optional<String> result = dialog.showAndWait();
        controller.processTextEdit(result, type, index);
    }


}
