package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.SingleRunInstruction;

import java.util.List;

public class Tangent extends SingleRunInstruction {

    private static final int numArgs = 1;

    public Tangent(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double val = paramsAsVals.get(0);
        valueOfExecution = Math.tan(Math.toRadians(val));
    }

    public String toString(double val){
        return instrName + " " + val + " = " + valueOfExecution;
    }
}
