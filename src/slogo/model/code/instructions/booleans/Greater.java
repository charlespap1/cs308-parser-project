package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Greater extends Instruction {

    private static final int numArgs = 2;

    public Greater(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = this.getParamsAsVals(t);
        double val1 = paramsAsInts.get(0);
        double val2 = paramsAsInts.get(1);
        valueOfExecution = val1 > val2 ? 1 : 0;
    }
}
