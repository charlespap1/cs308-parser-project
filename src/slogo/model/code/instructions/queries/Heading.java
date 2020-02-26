package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Heading extends Instruction {

    private static final int numArgs = 0;

    public Heading(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = (int) t.getAngle();
    }
}
