package slogo.model.code.instructions.multipleturtles;

import slogo.model.Turtle;
import slogo.model.code.instructions.TurtleAction;
import slogo.model.code.instructions.Instruction;


public class Id extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = Turtle::getId;

    public Id(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }

}
