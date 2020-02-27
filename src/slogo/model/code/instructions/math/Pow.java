package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Pow extends Instruction {

    private static final int numArgs = 2;

    public Pow(String name) {
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double base = paramsAsVals.get(0);
        double exponent = paramsAsVals.get(1);
        valueOfExecution = Math.pow(base, exponent);
        t.setCurrCommand(toString(base, exponent));
        t.setCurrCommand("");
    }

    public String toString(double val1, double val2){
        return val1 + " ^ " + val2 + " = " + valueOfExecution;
    }
}
