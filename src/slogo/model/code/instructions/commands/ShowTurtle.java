package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class ShowTurtle extends Instruction {

    private static final int NUM_ARGS = 0;

    public ShowTurtle(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        t.setVisible(true);
        this.valueOfExecution = 1;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName;
    }
}
