package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.SingleRunInstruction;

public class Pi extends SingleRunInstruction {

    private static final int numArgs = 0;

    public Pi(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = Math.PI;
    }
}
