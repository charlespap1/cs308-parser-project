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
        double currX = t.getXPos();
        double currY = t.getYPos();
        double distToHome = Math.sqrt(Math.pow(currX - HOME_X,2) + Math.pow(currY - HOME_Y,2));
        this.valueOfExecution = (int)distToHome;
        t.setLocation(HOME_X, HOME_Y);
        t.setCurrCommand("Cleared Screen");
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return "";
    }
}
