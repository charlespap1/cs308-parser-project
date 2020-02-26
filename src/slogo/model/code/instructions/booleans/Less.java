package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Less extends Instruction {

    private static final int numArgs = 2;

    public Less(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = this.getParamsAsVals(t);
        // TODO: check types
        double val1 = paramsAsInts.get(0);
        double val2 = paramsAsInts.get(1);
        valueOfExecution = val1 < val2 ? 1 : 0;
    }
}
