package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.SingleRunInstruction;

import java.util.List;

public class Pow extends SingleRunInstruction {

    private static final int numArgs = 2;

    public Pow(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double base = paramsAsVals.get(0);
        double exponent = paramsAsVals.get(1);
        valueOfExecution = Math.pow(base, exponent);
    }

    public String toString(double val1, double val2){
        return val1 + " ^ " + val2 + " = " + valueOfExecution;
    }
}
