package slogo.model.tokens;

import java.util.List;

public class Difference extends Instruction {

    private static final int numArgs = 2;

    public Difference(String name) {
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
