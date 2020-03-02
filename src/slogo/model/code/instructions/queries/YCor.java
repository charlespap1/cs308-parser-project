package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class YCor extends Instruction {
    private static final int numArgs = 0;

    public YCor(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void performAction(Turtle t) {
        valueOfExecution = -t.getYPos();
    }
}
