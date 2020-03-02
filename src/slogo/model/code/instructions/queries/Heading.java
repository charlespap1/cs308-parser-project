package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Heading extends Instruction {

    private static final int numArgs = 0;

    public Heading(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = t.getAngle();
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}
