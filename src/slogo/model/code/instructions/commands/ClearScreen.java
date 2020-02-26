package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class ClearScreen extends Instruction {

    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int numArgs = 0;

    public ClearScreen(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        //TODO: how will frontend know when this instruction is called and erase the turtle's trails?
        valueOfExecution = distFrom(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }
}
