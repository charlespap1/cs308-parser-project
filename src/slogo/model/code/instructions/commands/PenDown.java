package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenDown extends TurtleCommand {

    private static final int numArgs = 0;

    public PenDown(String name){
        super(numArgs);
        instrName = name;
    }

    protected void performAction (Turtle t) {
        t.setPenUp(false);
        valueOfExecution = 1;
    }

    @Override
    public String toString(){
        return instrName;
    }
}
