package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Not extends Instruction {

    private static final int NUM_ARGS = 1;

    public Not(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsInts = this.getParamsAsVals(t);
        int val = paramsAsInts.get(0);
        this.valueOfExecution = (val == 0) ? 1 : 0;
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
