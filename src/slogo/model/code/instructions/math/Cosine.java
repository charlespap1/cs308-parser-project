package slogo.model.code.instructions.math;

import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Cosine extends Instruction {

    private static final int numArgs = 1;

    public Cosine(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.cos(Math.toRadians(val));
    }
}
