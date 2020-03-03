package slogo.model.code.instructions.math;

import slogo.model.code.instructions.Instruction;

public class Pi extends Instruction {

    private static final int numArgs = 0;

    public Pi(String name) {
        super(numArgs);
        instrName = name;
    }

    public void execute () {
        valueOfExecution = Math.PI;
    }
}
