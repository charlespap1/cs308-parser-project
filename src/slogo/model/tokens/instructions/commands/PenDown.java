package slogo.model.tokens.instructions.commands;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

public class PenDown extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        t.setPenUp(false);
        return 1;
    };

    public PenDown(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

    @Override
    public String toString(){
        return instrName;
    }
}
