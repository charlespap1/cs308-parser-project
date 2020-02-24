package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenUp extends Instruction {

    private static final int numArgs = 0;

    public PenUp(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        t.setPenUp(true);
        this.valueOfExecution = 0;
        t.setCurrCommand("Pen Up");
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public String toString(){
        return "Pen Up";
    }
}
