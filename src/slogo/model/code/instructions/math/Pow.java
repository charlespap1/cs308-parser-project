package slogo.model.code.instructions.math;

import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Pow extends Instruction {

    private static final int numArgs = 2;

    public Pow(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double base = paramsAsVals.get(0);
        double exponent = paramsAsVals.get(1);
        return Math.pow(base, exponent);
    }
}
