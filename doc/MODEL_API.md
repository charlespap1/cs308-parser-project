#Model API

External API

Connects the backend to the Controller. This API specifies the
relationship between the backend and the Controller and defines
which methods the Controller can call on the Model.

Methods which every Model must implement:

List State executeCode(String)
* Inside of this method, the backend will parse the given 
String and develop a list of changing states accordingly 

void clearVariables()
* this method clears out the map of variables in the
backend to default values 