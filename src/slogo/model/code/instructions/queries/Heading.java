package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Heading extends Instruction {

    protected int NUM_ARGS = 0;

    public Heading(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = (int) t.getAngle();
    }
}
