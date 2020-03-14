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

Methods which every Static View Element must implement:

Node getView();
* Allows the setup to get the node of the element to add it to the root

void setTitleProperty(List<StringProperty> sp);
* Allows for the language of the text on element to by dynamically binded 
and change with the chosen language

 