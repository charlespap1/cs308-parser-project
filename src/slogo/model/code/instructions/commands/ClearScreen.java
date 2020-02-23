package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class ClearScreen extends Instruction {

    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) {
        //TODO: how will frontend know when this instruction is called and erase the turtle's trails?
        t.setProperties(HOME_X, HOME_Y, t.getAngle(), t.getIsPenUp());
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    //TODO: needs to return the distance turtle moved
    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}
