package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class TurtleShowingQuery extends Instruction {

    private static final int numArgs = 0;

    public TurtleShowingQuery(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = t.isVisible() ? 1 : 0;
    }
}
