#StaticViewElement API

Internal to Frontend 

We decided to scratch our BUTTON_API internal frontend API. When we
got to coding, we realized we didn't need any extra implementation or
features than those which are provided by the JavaFX Button class. Instead, 
we found that across many created data structures and different inheritances,
the super classes had some very similar methods. Our TurtleGraphicalMover, all
of our selectors and all of our ScrollingWindows have needs for a getView() and
a setTitleStringProperty() method. Thus, the StaticViewElement was borne from
this commonality across many of our structures. 

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
 