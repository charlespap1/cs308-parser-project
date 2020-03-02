package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Pi extends Instruction {

    private static final int numArgs = 0;

    public Pi(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = Math.PI;
    }
}
