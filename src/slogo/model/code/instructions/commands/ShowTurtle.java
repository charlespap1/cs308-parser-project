package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class ShowTurtle extends Instruction {

    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) {
        //TODO: create an isVisible parameter in Turtle and set it to true
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public int generateValue(){
        return 1;
    }

    public String toString(){
        return "";
    }
}
