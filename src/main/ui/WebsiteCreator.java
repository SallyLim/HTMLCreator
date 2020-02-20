package ui;

import model.NoElementException;
import model.StringElements;
import model.Title;
import model.WebPage;
import persistence.LoadPage;
import persistence.SavePage;

import java.io.IOException;
import java.util.Scanner;

import static persistence.SavePage.PAGELOCATION;

// Application to allow users to interactively create their own website
public class WebsiteCreator {
    private WebPage website;
    private Scanner userInput;

    //EFFECTS: runs the website creator app
    public WebsiteCreator() throws NoElementException, IOException {
        runWebsiteCreatorApp();
    }

    //MODIFIES: this
    //EFFECTS: runs app and performs user inputs until user wants to quit
    private void runWebsiteCreatorApp() throws NoElementException, IOException {
        userInput = new Scanner(System.in);
        String keyInput;

        loadPage();

        while (true) {
            displayOptions();

            keyInput = userInput.nextLine();

            if (keyInput.equals("q")) {
                returnHtmlFile();
                toSave();
                System.out.println("\nProgram quitting.");
                break;
            } else {
                doKeyInput(keyInput);
            }
        }

    }

    //EFFECTS: asks user if wants to save current state to file before quitting, if yes then saves to file
    private void toSave() throws IOException {
        System.out.println("\nWould you like to save your current website? Y/N");
        String yorN = userInput.nextLine();

        if (yorN.equals("Y") || yorN.equals("y")) {
            savePage();
        }
    }

    //EFFECTS: saves current version of page to PAGELOCATION
    private void savePage() throws IOException {
        SavePage save = new SavePage();

        String json = save.makeJson(website);
        save.toFile(json);

        System.out.println("\nFile has been successfully saved to" + PAGELOCATION);
    }


    //MODIFIES: this
    //EFFECTS: loads web page from PAGELOCATION; if no page is found, create new default web page
    private void loadPage() {
        LoadPage load = new LoadPage();

        try {
            String json = load.fromFileToJson();
            website = load.fromJsonToPage(json);
        } catch (IOException e) {
            website = new WebPage();
        }
    }

    //MODIFIES: this
    //EFFECTS: performs user inputs according to key press
    private void doKeyInput(String input) throws NoElementException, IOException {
        if (input.equals("t")) {
            changeTitle();
        } else if (input.equals("n")) {
            addText();
        } else if (input.equals("d")) {
            deleteText();
        } else if (input.equals("b")) {
            changeText();
        } else if (input.equals("f")) {
            changeTitleSize();
        } else if (input.equals("s")) {
            changeTextFontSize();
        } else if (input.equals("c")) {
            changeBannerColor();
        } else if (input.equals("x")) {
            savePage();
        } else if (input.equals("p")) {
            informCurrentState();
        } else {
            System.out.println("\nInvalid key.");
        }
    }

    //EFFECTS: prints out all possible options of action for user
    private void displayOptions() {
        System.out.println("\nCustomize your website further by pressing...");
        System.out.println("\nt - to change title");
        System.out.println("\nn - to add a new body of text");
        System.out.println("\nd - to delete a body of text");
        System.out.println("\nb - to change an existing body of text");
        System.out.println("\nf - to change the title's font size");
        System.out.println("\ns - to change the body of text's font size");
        System.out.println("\nc - to change the banner color");
        System.out.println("\nx - to save page to file");
        System.out.println("\n");
        System.out.println("\nPress p to see the current layout of your website");
        System.out.println("\nPress q to quit and receive html file");
    }

    //MODIFIES: this
    //EFFECTS: deletes a body of text
    private void deleteText() {
        System.out.println("\nEnter description of text box you would like to delete:");
        String textDescription = userInput.nextLine();

        try {
            website.removeTextBubble(textDescription);
            System.out.println("\nThe specified text box has been removed.");
        } catch (NoElementException n) {
            System.out.println("\nNo text box of the name has been found.");
        }
    }

    //MODIFIES: this
    //EFFECTS: changes title of page
    private void changeTitle() {
        StringElements title = website.getTextBubble("Title");
        String currentTitle = title.getText();

        System.out.println("\nThe current title is " + currentTitle);
        System.out.println("\nEnter new title: ");
        String newTitle = userInput.nextLine();

        title.setText(newTitle);
        String checkNewTitle = title.getText();
        System.out.println("\nThe new title is " + checkNewTitle);
    }

    //MODIFIES: this
    //EFFECTS: adds a BodyOfText to the page
    private void addText() {
        website.addTextBubble();
        System.out.println("\nEnter two word text description:");
        String textDescription = userInput.nextLine();
        System.out.println("\nThe text box has been named: " + textDescription);
        website.addTextDescription(textDescription);
    }

    //MODIFIES: this
    //REQUIRES: at least one text box on page
    //EFFECTS: changes text inside box of text
    private void changeText() {
        System.out.println("\nEnter description of text box you would like to edit:");
        String textDescription = userInput.nextLine();

        StringElements editingText = website.getTextBubble(textDescription);

        if (editingText != null) {
            System.out.println("\nEnter new text:");
            String newText = userInput.nextLine();

            editingText.setText(newText);
            String checkNewText = editingText.getText();
            System.out.println("\nThe text has been changed to: " + checkNewText);
        } else {
            System.out.println("\nText box name not found.");
        }
    }

    //MODIFIES: this
    //EFFECTS: changes text font size
    private void changeTitleSize() {
        StringElements title = website.getTextBubble("Title");
        int currentSize = title.getFontSize();

        System.out.println("\nThe title's current font size is " + currentSize);
        System.out.println("\nEnter a new integer font size:");
        int newSize = userInput.nextInt();

        title.setFont(newSize);
        int checkNewSize = title.getFontSize();
        System.out.println("\nThe title's font has been changed to: " + checkNewSize);
    }

    //MODIFIES: this
    //REQUIRES: has at least one text box
    //EFFECTS: changes text font size
    private void changeTextFontSize() {
        int numElements = website.getLengthOfElements();

        String testTextDescription = website.getDescription(2);
        StringElements testText = website.getTextBubble(testTextDescription);
        int currentSize = testText.getFontSize();
        System.out.println("\nThe current text font size is: " + currentSize);
        System.out.println("\nEnter a new integer font size:");
        int newSize = userInput.nextInt();

        for (int i = 2; i <= numElements; i++) {
            String currentTextDescription = website.getDescription(i);
            StringElements currentText = website.getTextBubble(currentTextDescription);
            currentText.setFont(newSize);
        }

        int checkNewSize = testText.getFontSize();
        System.out.println("\nThe text font size has been changed to " + checkNewSize);
    }

    //MODIFIES: this
    //EFFECTS: changes banner color
    private void changeBannerColor() {
        Title title = website.getTitle();
        String currentColor = title.getBanner();
        System.out.println("\nThe current banner color is: " + currentColor);
        System.out.println("\nEnter new banner color: ");
        String newColor = userInput.nextLine();

        title.changeBanner(newColor);
        String checkNewColor = title.getBanner();
        System.out.println("\nThe banner color has been changed to " + checkNewColor);
    }

    //EFFECTS: returns the html file version of web page
    private void returnHtmlFile() {
        String html = website.returnHtml();
        System.out.println(html);
    }

    //EFFECTS: informs current state like the number of elements and their names
    private void informCurrentState() {
        String currentTitle = website.getTextBubble("Title").getText();
        int numElements = website.getLengthOfElements();
        int numText = numElements - 1;

        System.out.println("\nYour website current has " + numElements + " elements.");
        System.out.println("\nYou have one title with the name: " + currentTitle);
        System.out.println("\nYou have " + numText + " text boxes.");

        if (numText > 0) {
            System.out.println("\nThe descriptions of the text boxes are: ");

            for (int i = 2; i <= numElements; i++) {
                System.out.println("\n" + website.getDescription(i));
            }
        }
    }

}
