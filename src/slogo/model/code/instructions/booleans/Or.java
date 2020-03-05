package slogo.model.code.instructions.booleans;

import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Or extends Instruction {

    private static final int numArgs = 2;

    public Or(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return (val1 != 0 || val2 != 0) ? 1 : 0;
    }
}
