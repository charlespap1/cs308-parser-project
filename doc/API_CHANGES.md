#API Changes

## Names + netids

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