package slogo.model.tokens.instructions.multipleturtles;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

import java.util.ArrayList;

public class Turtles extends Instruction {

    private static final int numArgs = 0;
    private int numTurtles;
    private TurtleAction myAction = t -> {
        numTurtles ++;
        return numTurtles;
    };

    public Turtles(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        numTurtles = 0;
        myAccessor.multiTurtleCommandToMaster(myAction, new ArrayList<>());
        return numTurtles;
    }
}
