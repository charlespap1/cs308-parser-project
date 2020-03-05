package slogo.model.code.instructions.multipleturtles;

import slogo.model.code.instructions.TurtleAction;
import slogo.model.code.instructions.Instruction;

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
