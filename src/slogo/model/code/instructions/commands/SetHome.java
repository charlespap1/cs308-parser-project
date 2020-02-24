package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0; // TODO: how to handle home/ (0,0) in center of window
    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
        t.setLocation(HOME_X, HOME_Y);
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }
    //TODO: needs to return the distance the turtle moved

    public String toString(){
        return "";
    }
}
