package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Or extends Instruction {

    private static final int NUM_ARGS = 2;

    public Or(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsInts = this.getParamsAsVals(t);
        int val1 = paramsAsInts.get(0);
        int val2 = paramsAsInts.get(1);
        this.valueOfExecution = (val1 != 0 || val2 != 0) ? 1 : 0;
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
