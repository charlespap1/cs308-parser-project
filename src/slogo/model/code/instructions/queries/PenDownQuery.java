package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenDownQuery extends Instruction {

    protected int NUM_ARGS = 0;

    public PenDownQuery(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        this.valueOfExecution = t.getIsPenUp() ? 0 : 1;
    }
}
