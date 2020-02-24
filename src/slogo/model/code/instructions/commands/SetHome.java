package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int NUM_ARGS = 0;

    public SetHome(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        this.valueOfExecution = (int)distFrom(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
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
