package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class PenDown extends Instruction {

    private static final int numArgs = 0;

    public PenDown(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        t.setPenUp(false);
        this.valueOfExecution = 1;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public String toString(){
        return instrName;
    }
}
