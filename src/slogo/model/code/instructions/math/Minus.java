package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Minus extends Instruction {

    private static final int numArgs = 1;

    public Minus(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double val = paramsAsVals.get(0);
        this.valueOfExecution = -val;
    }

    public String toString(double val){
        return instrName + " " + val + " = " + valueOfExecution;
    }
}
