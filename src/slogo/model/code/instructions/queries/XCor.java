package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class XCor extends Instruction {
    protected int NUM_ARGS = 0;

    public XCor(String name) {
        super();
        this.instrName = name;
    }

    public void execute(Turtle t) {
        this.valueOfExecution = t.getXPos();
    }
}
