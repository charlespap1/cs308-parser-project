package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Sine extends Instruction {

    protected int NUM_ARGS = 1;

    public Sine(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        // TODO: check type
        double val = paramsAsVals.get(0);;
        this.valueOfExecution = Math.sin(Math.toRadians(val));
    }
}
