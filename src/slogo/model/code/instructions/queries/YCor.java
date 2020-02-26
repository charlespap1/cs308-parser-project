package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class YCor extends Instruction {
    protected int NUM_ARGS = 0;

    public YCor(String name) {
        super();
        this.instrName = name;
    }

    public void execute(Turtle t) {
        this.valueOfExecution = t.getYPos();
    }
}
