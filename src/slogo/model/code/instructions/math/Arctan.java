package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Arctan extends Instruction {

    private static final int numArgs = 1;

    public Arctan(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double val = paramsAsVals.get(0);
        valueOfExecution = Math.atan(val);
        t.setCurrCommand(toString(val));
        t.setCurrCommand("");
    }

    public String toString(double val){
        return instrName + " " + val + " = " + valueOfExecution;
    }
}
