###Names of all people who worked on the project

* Natalie Novitsky
* Micheal Xue
* Charles Papandreou
* Juliet Yznaga
* Braeden Ward

###Each person's role in developing the project:

Juliet: Frontend, Text File Saving and Reading, Exception Handling

Braeden: Frontend

Natalie: Controller, Frontend Interactions, Backend Instruction Interpretation

Michael: Backend Instruction Development, Exception Handling, Coordination with Frontend

Charles: Backend Instruction Development, Exception Handling, API Developer

###What are the project's design goals, specifically what kinds of new features did you want to make easy to add
The primary design of our program is according to an MVC design with the goal of being the most flexible in terms of the Model and View ends. The View is expandable in terms of being able to added different things the user can input beyond just text including graphical movement and uploading commands, previous variables and text functions. We specifically made it easy to add new frontend features (visual elements) by creating new StaticViewElement objects and adding them to our SetupScreen. 

The Model is specifically designed to be able to account for additional commands, states and types of functions the user can input. The robust backend API structure is what allows for additional commands to be added simply and easily. All inputs are automatically considered Tokens as long as they implement a toString() and execute() method. Tokens are further divided into Constants, Variables, Functions and Instructions. So, it is easy to create one of these types by implementing the desired superclass. If you desire to make a completely new type of Token, then you must add a few more lines to help Identify this type, but as long as the new type implements a toString() and execute() method, it will fit into our backend parsing design of stack of stacks. 

As we envisioned in the beginning, our Controller which connects the Model to the View is the least flexible part of the program in that it always adhere to the same flows of execution: all information is passed between the front and backends at the beginning of the creation of a window, and the controller is only activated to pass information if a certain button is pushed. It is however flexible in terms of types of instructions and the different things it connects between the View and Model.

###Describe the high-level design of your project, focusing on the purpose and interaction of the core classes
At the highest level, our program uses the MVC design. However, these parts do not each represent three equal parts to the overall project. The Controller only consists of one class and is vastly shorter than both the Model and Visual. However, the Controller is the most important part in that it controls all communication between the front and back ends. The Controller contains instances of the View and Model API structures which allows it to link language, user input, history, variables and new turtles between the front and back ends. It links functions by calling backend methods and giving these calls as EventHandlers to the frontend buttons. 

###What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
The most ambiguous conditions we had to handle in this project all revolved around how to handle commands with multiple turtles. Several cases exemplify these decisions:
* Fd fd 50: Moves all active turtles forward 100; outer fd loops through all turtles, while inner fd only acts on a single turtle each time it is called.
* Setpc fd 1: Moves all active turtles forward 1; fd still acts as an outer loop here because it is the first looping command executed
* If less? Xcor 5 [ fd 50 ]: Moves all active turtles forward 50 if their x-coordinate is < 50; if statement loops through all active turtles and inner xcor and fd commands act on single turtle at a time
* Setpc xcor: Sets pen color to last active turtle’s x-coordinate (if this is a valid index); neither command loops through all turtles
* Tell [ 2 ] fd tell [ 3 ]: Moves turtle 2 forward 3 rather than resetting active turtle to 3 and then executing fd 3 on turtle 3; changing active turtles once inside a loop through all turtles  

We also made the assumption that pen up/down, pen color, pen thickness, and turtle image are not turtle specific qualities, but apply to all turtles when set. 

There are a fair amount of assumptions with the undo/redo functionality and history--clear from the front end clears history and all lines, while the clearscreen command centers the turtle and clears lines, but not history. Once lines are cleared, they cannot be brought back with undo. Undo/redo also occur at the program level i.e. will undo/redo all commands written in a single go, rather than one instruction at a time. 

###Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline
One feature we did not implement was the ability to set the preference for number of turtles and code file to load. To implement this feature, we would need to add more methods in the Interactions class (View interface) which could tell the Controller how many starting turtles were needed. This could then be applied by putting a for loop around the addTurtle() method in the Controller class and executing it as many times as needed. To load an initial file, we could use the existing method in the Interactions class which can get a file, but we would need to specify to grab the file read by the preference loader. Then, we would need to add a line in the Controller which loaded either a specified file or default blank file every time a new window was created. The getFile() method is quite encapsulated and closed because the Controller doesn’t know how the file is getting there, just that it has now been given a file and needs to pass it to the backend to parse. The idea of creating a for loop around the addTurtle() method is less closed because it requires the Controller to know how many turtles are necessary. A dependency is created in that the Controller must call a method such as getNumTurtlesNeeed() in order to accomplish this goal. Both of these extra features make the design of the preference specifier less encapsulated because now the Controller has to do setup work. Before, all of the specifications could be performed in the frontend without the Controller knowing exactly what was being specified. Since these two features require communication with the backend, now the Controller has to do more work to set up the game. 

Another feature from the specification we did not implement in detail was animating the turtle. Currently, our turtle’s x and y coordinates are binded to a backend turtle, so whenever the backend turtle is updated, the frontend turtle is also automatically updated. We could put a pause between instructions so that the turtle doesn’t look like it executes everything all at once. This could be easily done by adding a delay after every instruction is executed. On the Internet I found that to add a delay, we could use the sleep() function of the TimeUnit class to delay for a certain number of milliseconds like so: 

```java
TimeUnit.MILLISECONDS.sleep(10);
```

Implementation in this way would be closed and would only require a single import in the backend. However, this would not solve the problem of animation for single instructions such as FD 50. There would be a delay at the end, but the turtle would still jump from 0 to 50 without animation. Now I am thinking that a better way to go about this would be to separate the turtle moving image from the actual frontend turtle. We could have the turtle image operating on a animation timeline and have it move by a little bit at a time until it reached the desired coordinates of the frontend turtle. This would require the rest of the features of the game to be paused during the turtle movement time. We would have to change our design and create a class which would handle timeline and animation structures then apply them to the turtle face. By creating a standalone class which handled animation and by applying it to every frontend turtle as soon as the turtle is create, I believe we could add this feature without overhauling our entire design. 

The easiest feature to add due to our design is new Instructions. First you must create a new subclass representing that instruction extending the Instruction superclass. This class must have a toString() method and an execute() method which defines what the instruction does with its parameters. Next, you need to add the key for this instruction to the CodeFactory and to all of the language resources. Once you have done this, the desired instruction will be able to be executed within our stack of stacks backend design. 
