package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Pi extends Instruction {

    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) {
        // nothing
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public int generateValue(){
        //return Math.PI;
        return 0;
    }

    public String toString(){
        return "";
    }
}
