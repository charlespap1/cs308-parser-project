package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Remainder extends Instruction {

    private static final int numArgs = 2;

    public Remainder(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        valueOfExecution = val1 % val2;
    }

    public String toString(double val1, double val2){
        return val1 + " % " + val2 + " = " + valueOfExecution;
    }
}
