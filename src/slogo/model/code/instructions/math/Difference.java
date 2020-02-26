package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Difference extends Instruction {

    private static final int numArgs = 2;

    public Difference(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        // TODO: check types
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        this.valueOfExecution = val1 - val2;
    }
}
