package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class ClearScreen extends Instruction {

    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int NUM_ARGS = 0;

    public ClearScreen(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        //TODO: how will frontend know when this instruction is called and erase the turtle's trails?
        this.valueOfExecution = (int)distFromHome(t.getXPos(),t.getYPos());
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
