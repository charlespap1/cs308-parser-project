package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenDown extends Instruction {

    private static final int numArgs = 0;

    public PenDown(String name){
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        t.setPenUp(false);
        valueOfExecution = 1;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }
}
