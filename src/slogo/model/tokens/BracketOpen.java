package slogo.model.tokens;

import slogo.model.tokens.instructions.Instruction;

public class BracketOpen extends Instruction {

    private static final int numArgs = -1;

    public BracketOpen(String name){
        super(numArgs);
    }

    @Override
    public double execute() {
        return -1;
    }

    @Override
    public String toString() { return " [ "; }
}
