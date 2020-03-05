package slogo.model.tokens.instructions.math;

import slogo.model.tokens.instructions.Instruction;

import java.util.List;

public class Log extends Instruction {

    private static final int numArgs = 1;

    public Log(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.log(val);
    }
}
