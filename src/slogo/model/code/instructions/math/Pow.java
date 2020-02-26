package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Pow extends Instruction {

    private static final int numArgs = 2;

    public Pow(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double base = paramsAsVals.get(0);
        double exponent = paramsAsVals.get(1);
        this.valueOfExecution = Math.pow(base, exponent);
    }
}
