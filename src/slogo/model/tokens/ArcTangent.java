package slogo.model.tokens;

import java.util.List;

public class ArcTangent extends Instruction {

    private static final int numArgs = 1;

    public ArcTangent(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.atan(val);
    }
}
