package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class And extends Instruction {

    private static final int numArgs = 2;

    public And(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = this.getParamsAsVals(t);
        double val1 = paramsAsInts.get(0);
        double val2 = paramsAsInts.get(1);
        valueOfExecution = (val1 != 0 && val2 != 0) ? 1 : 0;
    }
}
