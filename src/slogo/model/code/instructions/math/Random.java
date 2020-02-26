package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Random extends Instruction {

    protected int NUM_ARGS = 1;

    public Random(String name) {
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double max = paramsAsVals.get(0);
        this.valueOfExecution = Math.random() * max;
    }
}
