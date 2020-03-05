package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.SingleRunInstruction;

import java.util.List;

public class Random extends SingleRunInstruction {

    private static final int numArgs = 1;

    public Random(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double max = paramsAsVals.get(0);
        valueOfExecution = Math.random() * max;
    }

    public String toString(double max){
        return instrName + " " + max + " = " + valueOfExecution;
    }
}
