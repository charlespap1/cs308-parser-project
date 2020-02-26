package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    protected int NUM_ARGS = 0;

    public SetHome(String name){
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = distFrom(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }
}
