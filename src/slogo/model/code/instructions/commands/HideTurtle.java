package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class HideTurtle extends Instruction {

    private static final int NUM_ARGS = 0;
    
    public HideTurtle(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        t.setVisible(false);
        this.valueOfExecution = 0;
        t.setCurrCommand("Hide Turtle");
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return "";
    }
}
