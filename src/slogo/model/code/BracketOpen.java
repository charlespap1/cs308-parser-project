package slogo.model.code;

import slogo.model.code.instructions.Instruction;

public class BracketOpen extends Instruction {

    private static final int numArgs = -1;

    public BracketOpen(String name){
        super(numArgs);
    }


    public double execute() {
        return -1;
    }
}
