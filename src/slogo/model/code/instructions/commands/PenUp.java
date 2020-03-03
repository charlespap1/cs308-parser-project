package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenUp extends TurtleCommand {

    private static final int numArgs = 0;

    public PenUp(String name){
        super(numArgs);
        instrName = name;
    }

    protected void performAction (Turtle t) {
        t.setPenUp(true);
        valueOfExecution = 0;
    }

    @Override
    public String toString(){
        return instrName;
    }
}
