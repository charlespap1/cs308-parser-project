package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Pi extends Instruction {

    protected int NUM_ARGS = 0;

    public Pi(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = Math.PI;
    }

}
