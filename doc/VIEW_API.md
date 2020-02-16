#View API

External API

Connects the front end to the Controller. This API specifies the
relationship between the frontend and the Controller and defines
which methods the Controller can call on the Visual. 

Methods which every View must implement:

String getInstruction()
* Grabs whatever is currently in the text field which
exists in the Visual. This is how the Controller will
get the String for the backend to parse

void updateDisplay(State s)
* Send a new state to the frontend to update the turtle
and its movement on the screen

void showError(String)
* Connects to the fronend to display the error message which 
the backend encountered with the user input 
