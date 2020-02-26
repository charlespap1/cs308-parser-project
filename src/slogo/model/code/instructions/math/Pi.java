package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Pi extends Instruction {

    private static final int numArgs = 0;

    public Pi(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = Math.PI;
    }

}
