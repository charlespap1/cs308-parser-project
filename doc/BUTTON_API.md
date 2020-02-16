#Button API

Internal to Frontend 

This will allow us to easily create new buttons
and streamline their implementation in the Visual
class. For each type of button that implements this
interface, it will have an execute() method which will
perform its specific function. Thus, we will be able to
just call SomeButton.execute() instead of specifying 
a different method for each type of button. This
will simplify the code in the Visual class because
no extra methods for each function need to be specified
within it.

Methods which every Button must implement:

void execute()
 * Executes whatever that button needs to do whether
 that be "stop" "go" or "stop turtle"