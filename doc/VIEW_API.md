#View API

External API

Connects the front end to the Controller. This API specifies the
relationship between the frontend and the Controller and defines
which methods the Controller can call on the Visual. 



Methods which every View must implement:

void updateDisplay(State s) --> this method was included in our original 
Plan for our API but has since been deleted due to our preference to use binding 
* Send a new state to the frontend to update the turtle
and its movement on the screen

String getInstruction()
* Grabs whatever is currently in the text field which
exists in the Visual. This is how the Controller will
get the String for the backend to parse


void showError(String)
* Connects to the frontend to display the error message which 
the backend encountered with the user input 


File getFile();
* Delivers a file which the user specified to be executed 
in the backend

StringProperty getLanguageChoice();
* Needed to tell the backend which language we are in so that
they can use the correct resource bundle to identify instructions
 
DisplayAction getAction(String method);
* Uses reflection to call the needed method

String getNewWindowPreferences();
* Gets the preference the User specifies in the frontend to create
a new window in the controller

void addTurtle(slogo.model.Turtle turtle);
* Connects a newly made backend turtle to a new frontend turtle

void setGoButton(EventHandler<ActionEvent> goAction);
* Gives the go button the action of handing the user input
to the backend

void setNewWindowButton(EventHandler<ActionEvent> newWindowAction, Stage stage);
* Gives the go button of the new window popup the action of creating a 
new window, also allows popup to be displayed on the current stage

void setLoadTextFileButton(EventHandler<ActionEvent> newWindowAction, Stage stage);
* Gives the load text button the ability to hand off the loaded text to the backend,
also allows the popup to be displayed on the current stage 

void setLoadVarsAndCommandsButton(EventHandler<ActionEvent> newWindowAction, Stage stage);
* Gives the load variables button the ability to put the loaded variables into the 
backend, also allows popup to be displayed on the current stage 

void setSaveTextFileButton(Stage stage);
* Allows the popup to be displayed on the current stage. Doesn't need any controller
methods so doesn't need to be passed an EventHandler


void setUndoAction(EventHandler<ActionEvent> undoAction);
* Allows undo action to occur in front an backend when you click the frontend button

void setRedoAction(EventHandler<ActionEvent> redoAction);
* Allows redo action to occur in front an backend when you click the frontend button

void setClearHistory(EventHandler<ActionEvent> clearAction);
* Allows clear action to occur in front an backend when you click the frontend button

    void setDirectInstructionExecutor(DirectExecutor executor);

void setErrorMessage(StringProperty error);
* Binds the frontend error message to the backend so both sides can throw exceptions

void setPreferences(String preferences);
* Sets the preferences of the newly created window

void setViewLists(ObservableList<Token> variableList, ObservableList<Token> newCommandList);
* Connects the front and backend new command and variable lists 

void setupHistory(ObservableList<Token> historyList, BooleanProperty undoDisabled, BooleanProperty redoDisabled);
* Connects the front and backend history list and allows for clicking to execute