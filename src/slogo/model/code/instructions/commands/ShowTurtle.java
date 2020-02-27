package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class ShowTurtle extends Instruction {

    private static final int numArgs = 0;

    public ShowTurtle(String name){
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) {
        t.setVisible(true);
        valueOfExecution = 1;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }
}
