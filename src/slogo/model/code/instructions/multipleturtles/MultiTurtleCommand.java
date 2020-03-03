package slogo.model.code.instructions.multipleturtles;

import slogo.controller.AddNewTurtleFunction;
import slogo.model.code.instructions.Instruction;

public abstract class MultiTurtleCommand extends Instruction {
    protected AddNewTurtleFunction addNewTurtleFunction;

    public MultiTurtleCommand(int numArgs) {
        super(numArgs);
    }

    public void setAddNewTurtleFunction(AddNewTurtleFunction function){ addNewTurtleFunction = function; }
}
