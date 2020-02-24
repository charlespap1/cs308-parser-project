package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Pow extends Instruction {

    private static final int NUM_ARGS = 2;

    public Pow(String name) {
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsVals = this.getParamsAsVals(t);
        int base = paramsAsVals.get(0);
        int exponent = paramsAsVals.get(1);
        this.valueOfExecution = (int) Math.pow(base, exponent);
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
