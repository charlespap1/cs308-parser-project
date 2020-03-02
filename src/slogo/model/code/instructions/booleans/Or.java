package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Or extends Instruction {

    private static final int numArgs = 2;

    public Or(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        valueOfExecution = (val1 != 0 || val2 != 0) ? 1 : 0;
    }

    public String toString(double val1, double val2){
        return val1 + " " + instrName + " " + val2 + " = " + valueOfExecution;
    }
}
