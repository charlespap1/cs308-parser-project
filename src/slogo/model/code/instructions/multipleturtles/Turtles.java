package slogo.model.code.instructions.multipleturtles;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Turtles extends Instruction {

    private static final int numArgs = 0;

    public Turtles(String name){
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) throws InvalidArgumentException, InvalidLoopConditionException {
        // valueOfExecution = turtleMap.keySet().size();
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}