package slogo.model.tokens.instructions.math;

import slogo.model.tokens.instructions.Instruction;

import java.util.List;

public class Power extends Instruction {

    private static final int numArgs = 2;

    public Power(String name) {
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
