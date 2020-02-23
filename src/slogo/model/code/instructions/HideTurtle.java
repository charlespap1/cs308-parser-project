package slogo.model.code.instructions;

import slogo.model.Turtle;

public class HideTurtle extends Instruction {

    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) {
        // TODO: add an isVisible property to turtle and set it to false
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public int generateValue(){
        return 0;
    }

    public String toString(){
        return "";
    }
}
