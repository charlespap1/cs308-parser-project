package slogo.model.code.instructions.queries;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class YCor extends Instruction {

    private static final int NUM_ARGS = 0;

    public YCor(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        this.valueOfExecution = (int) t.getYPos();
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
