package slogo.model.code.instructions.multipleturtles;

import slogo.controller.AddNewTurtleFunction;
import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;
import java.util.Map;

public abstract class MultiTurtleCommand extends Instruction {
    protected AddNewTurtleFunction addNewTurtleFunction;
    protected Map<Integer, Turtle> turtleMap;
    protected List<Turtle> activeTurtles;

    public MultiTurtleCommand(int numArgs) {
        super(numArgs);
    }

    public void setAddNewTurtleFunction(AddNewTurtleFunction function){ addNewTurtleFunction = function; }
    public void setTurtleMap(Map<Integer, Turtle> turtlesMap) { turtleMap = turtlesMap; }
    public void setActiveTurtles(List<Turtle> activeTurtle) { activeTurtles = activeTurtle; }
}
