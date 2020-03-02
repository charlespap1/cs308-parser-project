package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenDownQuery extends Instruction {

    private static final int numArgs = 0;

    public PenDownQuery(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = t.getIsPenUp() ? 0 : 1;
    }
}
