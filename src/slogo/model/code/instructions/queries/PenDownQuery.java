package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenDownQuery extends Instruction {

    private static final int NUM_ARGS = 0;

    public PenDownQuery(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        this.valueOfExecution = t.getIsPenUp() ? 0 : 1;
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
