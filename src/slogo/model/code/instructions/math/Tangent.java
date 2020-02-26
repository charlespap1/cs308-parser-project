package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Tangent extends Instruction {

    private static final int numArgs = 1;

    public Tangent(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        // TODO: check type
        double val = paramsAsVals.get(0);
        this.valueOfExecution = Math.tan(Math.toRadians(val));
    }
}
