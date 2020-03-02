package slogo.model.code;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class BracketOpen extends Instruction {
    private static final int numArgs = -1;
    public BracketOpen(String name){
        super(numArgs);
    }

    public void execute(Turtle turtle) {}

    @Override
    public void performAction(Turtle turtle) {}

    public double generateValue() {
        return -1;
    }
}
