# API Changes

### Charles Papandreou (cnp20), Braeden Ward (bmw54), Juliet Yznaga (jay18), Michael Xue (myx2), Natalie Novitsky (nen4) 

### Backend

The backend APIs have changed in several ways since beginning this project.
The biggest change is that now we have a Token API in order to account
for functionality that encompasses all tokens, including Variables, Constants,
Instructions, Lists, etc. In particular, every token must be able to execute
and produce a return value, as well as a way to print itself so that it 
can be represented in the History/Variable/Command panels. 

For the Model API, our main method executeCode has stayed the same,
but we added functionality for getting variable and command lists so 
that they can be displayed on the frontend.

We also added a TurtleMasterAccessor API and AddToListFunction functional interface
to enable different instructions to delegate their execution to the TurtleMaster.

### Frontend 

#### View API
Our View API experienced many changes during the duration of our project. Initially,
we envisioned our API only needed three methods to do three tasks:
1. Get input from user
2. Show error message from backend exception
3. Update the display of the frontend turtle to match the new x,y coordinates

During the first sprint, we decided to use binding to update and link the frontend
turtle to the backend. As such, we were able to delete the updateDisplay() method (because
the update would automatically happen due to binding). Additionally, binding forced us
to establish methods which would set up the preferences and history lists to be linked
with the backend lists. 

After the first sprint, when we were asked to implement many more
frontend-backend communicating features due to the new Complete requirements, our API 
grew enormous. To add preferences, multiple turtles, Undo/Redo, change a language, and Load/Save text files,
we had to outline many more functions of our API. Most of these extra functions
involved setting frontend buttons to perform actions that take place in the controller or
backend (i.e. making a new window or delivering the contents of the text file to an 
instruction executor). Most of these methods are in the large chunk of void
methods in the View.java class. 


#### Internal Frontend API

When we developed our plan in the beginning of this project, we could not think
of a useful internal frontend API. Mostly for the sake of having it, we came up with
a Button API which would define all of our frontend buttons. We thought that one advantage
to this implementation would be that we could abstract away the internal function of
the button. Whenever a button is pressed, we could just call that button's .execute() 
method and all of the behind the scenes work could be obscured. However, once the frontend
people started building, they realized that is was cumbersome to create new classes for every button
and that the JavaFX button was definitely sufficient to accomplish all of our goals. 

It wasn't until actually working on the project and building frontend elements that we realized
what would be useful. To define the basic function of all of the windows on our screen, we
developed a Static View Element API. While coding, realized that all of our elements needed a mehtod
to get its view (get the node which represented it to be added to a root) and a method to 
bind its text so it could change based on the given language. Thus, we created a Static View Element
API which defined these functions. As a result, we were able to streamline the code
which instantiated all of these elements in our Setup class. In the beginning, we had to 
explicitly call the getView() method of every element to add it to the root. Adding this
API allowed us to keep a list of Static View Elements and add all of them using a for loop.