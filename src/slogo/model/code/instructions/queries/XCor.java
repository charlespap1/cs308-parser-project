package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class XCor extends Instruction {

    private static final int NUM_ARGS = 0;

    public XCor(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        this.valueOfExecution = (int) t.getXPos();
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
