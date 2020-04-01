# Website Builder

## Create a HTML file for your own personalized website

The application will allow users to **customize their web page** including and not limited to:
- Title
- Title banner color selection
- Body of text
- Styling of text

This application will be useful for people wanting to create their own website to share interesting information 
to the world. It could be to blog cool summer trips, or it could be to display their personal endeavors. I am 
pursuing this project because I love the idea of allowing people to share information more easily.

## User Stories

**FUNCTIONALITY**

As a user, I want to be able to:
- Add a title to the top of the web page
- Add a body of text to the web page
- Change the background color of the banner under the title
- Change the text size of my text on the web page

**DATA PERSISTENCE**

As a user, I want to be able to:
- save all the elements of my website at anytime and when quitting of the application
- have previous session loaded when application is opened

## Instructions for Grader
- You can generate the first required event by ... 
> When app is run for the first time, the title is already generated. It can be seen on the top of the html viewer in 
  the middle. You an do other edits by clicking on the edit menu on the right or clicking on title on the left hand 
  layout menu.
  
- You can generate the second required event by...
> Add a body of text to the web page by clicking on "Add new body of text" at the edit control bar on the right side
  under "BODY OF TEXT edits". Afterwards, you will be able to see that a new body of text has been added on the layout 
  menu on the left side under "Body of text". You can also see the change in the html viewer in the middle. You an do 
  other edits by clicking on the edit menu on the right or clicking on title on the left hand layout menu.
  
- You can locate my visual component by...
> The html viewer in the middle of the app constantly updates to show what the page will look like.

- You can save the state of my application by...
> The "Save" button on the top left can be used to save progress. When pressing "Quit", it will also ask you if you 
  want to save before quitting as well.
  
- You can reload the state of my application by...
> When the application is reopened, the previous session will be automatically loaded, real-time changes can be viewed
  in the html viewer.
  
## Phase 4: Task 2
- Included a type hierarchy in my code:
> The type hierarchy consists of the StringElements Interface which is implemented by the following classes: BodyOfText
  and Title.

## Phase 4: Task 3
- Changes made to improve the design of my code after a design analysis:
1. I extracted an Interface out of the Title and BodyOfText classes called the 
   StringCreator. This new interface houses two methods regarding extracting strings out of different StringElements
   and I made this change because I thought it made sense to separate the task of doing modifications to the Elements
   themselves from creating HTML code or extracting strings to disply in the UI from the Elements.
   
2. I split my GUI Controller class into different classes according to their different responsibilities. The Controller
   class that used to have everything now only starts the application and handles user inputs with event handlers.
   The physical edits to the back end of the program after the user identifies changes wanting to be done is done by 
   the Editor class. The Configure class does configurations when the application starts and is responsible for the
   configuration of the SwingContent html viewer and the edit menu bars' configurations.