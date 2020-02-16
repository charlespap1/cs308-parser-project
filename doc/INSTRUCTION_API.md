#Instruction API
Internal to the Backend

Umbrella  for different types of instructions we can do (forward, backward, 
rotate, etc.). This will allow us to create many different types of instructions
easily by creating different types of command objects which just extend this
interface.  

Methods which every instruction must implement: 

List State execute(State current, List Variables vars) 
* returns new state based on the current state inputted 

List<State> getNeededVariableNames()          
* returns list of Strings of the variables which this 
particular instruction modifies 

Example classes which implement this:
ForwardInstruction
BackwardInstruction  
LeftInstruction  
RightInstruction  
RotateInstruction 
PenUpInstruction 
PenColorInstruction 