# Refactoring Discussion

## Charles Papandreou (cnp20), Braeden Ward (bmw54), Juliet Yznaga (jay18), Michael Xue (myx2), Natalie Novitsky (nen4) 

After reading over the issues found by the static analysis tool, we found
several important issues.

First, our longest method was 31 lines. Although not inordinately long,
there was some duplicated logic, as the method was primarily about
setting up different buttons and views. This could potentially be 
improved by creating another method that handles duplicated logic.

Our second major issue was magic numbers in both the frontend and backend.
This will be solved by simply replacing those magic numbers with static final
variables declared at the top of the class.

Our third major issue was that exception handlers should preserve the
original exceptions. Right now, many of our exception handlers simply
catch general Exceptions rather than specific ones. This could be 
improved by changing those handlers to account for specific exceptions
so that the code can be more understandable.