package slogo.model.tokens;

import java.util.List;

public class NotEqual extends Instruction {

    private static final int numArgs = 2;

    public NotEqual(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 != val2 ? 1 : 0;
    }
}
