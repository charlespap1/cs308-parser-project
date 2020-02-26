package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Minus extends Instruction {

    protected int NUM_ARGS = 1;

    public Minus(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        // TODO: check types
        double val = paramsAsVals.get(0);
        this.valueOfExecution = -val;
    }
}
