package slogo.model.tokens;

import slogo.model.exceptions.DivideByZeroException;

import java.util.List;

public class Quotient extends Instruction {

    private static final int numArgs = 2;

    public Quotient(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        if (val2 == 0 && val1 != 0) throw new DivideByZeroException();
        return val1 / val2;
    }
}
