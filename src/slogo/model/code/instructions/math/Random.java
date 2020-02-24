package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Random extends Instruction {

    private static final int NUM_ARGS = 1;

    public Random(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsVals = this.getParamsAsVals(t);
        int max = paramsAsVals.get(0);
        this.valueOfExecution = (int) (Math.random() * max);
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
