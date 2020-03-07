parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Natalie Novitsky, Micheal Xue, Charles Papandreou, Juliet Yznaga, Braeden Ward


### Timeline

Start Date: 2/13/20

Finish Date: 3/6/20

Hours Spent: 300+ (~20/person/week)

### Primary Roles

Juliet: Frontend, Text File Saving and Reading, Exception Handling
Braeden: Frontend
Natalie: Controller, Frontend Interactions, Backend Instruction Interpretation
Michael: Backend Instruction Development, Exception Handling, Coordination with Frontend
Charles: Backend Instruction Development, Exception Handling, API Developer


### Resources Used

[Great Javafx Resource](http://tutorials.jenkov.com/javafx/button.html)

[Reading and Creating text files in Java](https://www.geeksforgeeks.org/different-ways-reading-text-file-java/)

### Running the Program

In order to run the program, run the main method in the Controller class.

Main class: Controller

#### Files used to Test and Expected Error:

Our program handles bad input (almost any kind of string with the wrong syntax) by alerting the user in an error message and handles missing files (by using a default file) wherever it loads a file specified by user input and saves default values if necessary values are not found in a file. This covers almost all possible user-caused errors in this program. We also have checks to make sure the display methods and  command classes are found when we use reflection and other internal errors.

Our program also has the option of adding a preference resource bundle. This bundle sets the initial turtle image, pen color, penUp and background color. For all of these attributes, if they are not found within the specified resource bundle, they are set to default values so the program can continue to run.

There is also exception handling in the frontend which will alert the user if they try to load a text file that does not exist in the resources or if there is a problem while saving or building their file.

You can test the loading file aspect by loading the included circle, flower or spiral.txt files while in the environment. When loading files, simply type the filename, excluding the .txt extension.

In the backend, our program checks that when an instruction is typed, the parameters are correct. This includes having the correct number of parameters, having parameters of correct type given an instruction, invalid commands, and more. These errors will be displayed to the user as explained before.


#### Data files needed:

Contents of parser_parser_team21/resources/ folder (airplane.png, arrow.png, car.png, dog.png, exit.png, go.png, main.css, turtle.png and any example .txt files to load)
* Example txt files should just be strings written in proper SLogo syntax to make new commands, make variables, etc. This is how our program saves and loads new commands and variables.

Contents of parser_parser_team21/src/resources folder
* colors props files with indexes to colors/image paths for the palette (BackgroundColors, TurtleImages)
* commands props files with keys to commands in different languages, keys to method names for display commands, and keys to common commands to display (Chinese, CommandSorter, CommonCommands, English, French, German, Italian, Methods, Portuguese, Russian, Spanish, Syntax, Urdu)
* labels props files with keys to front end labels by language (Chinese, English, French, German, Italian, Portuguese, Russian, Spanish, Urdu)
* preferences props file with default values for front end setup (DefaultPreferences)


#### Features implemented:
* The primary way of moving the turtle is by entering commands into the white text field and pressing the go button
* Click on commands in history to execute, click on new commands in the new commands list to bring them into the input box and replace zeros with values for variables, click on variable names to bring up an input box to set the value from the front end
* Hover over turtles to see their ID, location, and angle
* Change language for commands and all visual text using dropdown menu
* Deactivate or activate a turtle by clicking on it
* Load and save commands with the top load and save buttons and load and save variables/newly created commands with the bottom load and save buttons
* See more information on different possible instructions by clicking on the top right-hand corner. This page gives common commands as well as a link to the CS308 page where all commands are specified
* If you change the color of an index in your palette, the color of the button with that index will change
* After having executed a command, the redo and undo button can be used to move back through the history of programs that have been run, moving the turtles and lines to their appropriate states.
* Change background/pen color/character type/etc. on the bottom and bottom left



### Notes/Assumptions

Assumptions or Simplifications:

The most ambiguous conditions we had to handle in this project all revolved around how to handle commands with multiple turtles. Several cases exemplify these decisions:
* Fd fd 50: Moves all active turtles forward 100; outer fd loops through all turtles, while inner fd only acts on a single turtle each time it is called.
* Setpc fd 1: Moves all active turtles forward 1; fd still acts as an outer loop here because it is the first looping command executed
* If less? Xcor 5 [ fd 50 ]: Moves all active turtles forward 50 if their x-coordinate is < 50; if statement loops through all active turtles and inner xcor and fd commands act on single turtle at a time
* Setpc xcor: Sets pen color to last active turtle’s x-coordinate (if this is a valid index); neither command loops through all turtles
* Tell [ 2 ] fd tell [ 3 ]: Moves turtle 2 forward 3 rather than resetting active turtle to 3 and then executing fd 3 on turtle 3; changing active turtles once inside a loop through all turtles

We also made the assumption that pen up/down, pen color, pen thickness, and turtle image are not turtle specific qualities, but apply to all turtles when set.

There are a fair amount of assumptions with the undo/redo functionality and history--clear from the front end clears history and all lines, while the clearscreen command centers the turtle and clears lines, but not history. Once lines are cleared, they cannot be brought back with undo. Undo/redo also occur at the program level i.e. will undo all commands written in a single go, rather than one instruction at a time.

Interesting data files:

One great file to test with our program is the included /procedures_with_parameters/flower.logo. You can load this file by clicking Load Configuration and typing “flower”. This will load newly created functions, text, and variables. By clicking on the function flower, then specifying a size of 3, 4 or 5 (by replacing the dummy 0) and clicking “Go”, you can see how the function will generate flowers of different sizes.


#### Known Bugs:

One known bug is that our error messages only appear in english. We used the format that each error message contains a getMessage() which returns a message specified by a static instance variable in its class.

Another bug is that if you click on the variable Viewer before there are any inputted variables, it will throw and error. This is because the variable viewer thinks you’re trying to set a variable when there aren’t any variables available. We have an exception to catch this; However, since the method is only called within the variable viewer, it does not have access to the currentErrorMessage which usually displays our error message. Thus the user gets no feedback but an error occurs on the console so they cannot see it.

Additionally, our German turtle commands on the graphical viewer do not work. We belive this is due to the interesting syntax of the Forward, Back, Left and Right commands specified by the German properties ResourceBundle. The words include an interesting character shaped like a diamond with a question mark in the middle. We are unsure how to type these commands in and believe that there is an error with trying to parse this strange character. One problem shooting for this would be to look up the word and change that character to one of the 26 known characters and see if the bug still exists.

Less of a bug and more of an important note is that there are still many magic values in the frontend. In order to have ratios, we’d divide instance variable values by random integers. We felt it was unnecessary to make different instance variables for every single ratio so there are a decent number of magic values in our frontend.

#### Extra credit:

We decided to take on the challenging feature of making all our visual text change depending on the language specified. To do this, we specified all of our written labels to be bound to StringProperties. Thus, depending on the language specified, we used a LanguageHelper object to bind button text properties and visual object titles to the new language value for that item. As a result, we have many instance variables in our SetupScreeen class to specify keys. In the future, we hope to find a way to use a smart data structure to cut down on this code.

### Impressions

In the beginning of this assignment, we had a great flow of work and data between the front and back ends through the COntroller. However, the new Complete objectives required much more information to travel between the two ends. As a result, our Controller had to accommodate and now contains a fat, kinda smelly method to set all of our front end buttons to functions in the backend.  In the future, it would be great to find a way to cut down on all of this deliberate setting so that our Controller could once again function as a streamlined network between front and back. Additionally, we ran into this problem of trying to do too much with our SetupScreen class. We broke it up into three different classes to deal with setup, but if we would have known the extent to the complete in the beginning, perhaps we could have been more deliberate in our construction. We might have further democratized objects which interacted more frequently together to form a more streamlined hierarchy. This was not a flaw in the assignment, but it is something we will definitely be thinking about in the future when we plan out our next project.

In the future, it might be nice to have the lab on APIs and reflection before we start the project so that we have a better understanding going in. This way, we could develop a more strategic plan. While constructing our initial plan, we did not have a great grasp on the idea of APIs. As a result, we were not able to come up with a helpful, applicable internal frontend API. However, once we actually coded and better understood the
