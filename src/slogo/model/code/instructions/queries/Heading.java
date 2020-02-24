package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Heading extends Instruction {

    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) {
        // no functionality
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    //TODO: needs to have access to the turtle so it can return the turtle's angle
    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}
