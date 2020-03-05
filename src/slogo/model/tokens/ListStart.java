package slogo.model.tokens;

import slogo.model.tokens.instructions.Instruction;

public class ListStart extends Instruction {

    private static final int numArgs = -1;

    public ListStart(String name){
        super(numArgs);
    }

    @Override
    public double execute() {
        return -1;
    }

    @Override
    public String toString() { return " [ "; }
}
