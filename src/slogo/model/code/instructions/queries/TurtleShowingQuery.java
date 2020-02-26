package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class TurtleShowingQuery extends Instruction {

    protected int NUM_ARGS = 0;

    public TurtleShowingQuery(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = t.isVisible() ? 1 : 0;
    }
}
