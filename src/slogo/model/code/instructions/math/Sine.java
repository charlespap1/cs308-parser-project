package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Sine extends Instruction {

    private static final int numArgs = 1;

    public Sine(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        // TODO: check type
        double val = paramsAsVals.get(0);;
        valueOfExecution = Math.sin(Math.toRadians(val));
        t.setCurrCommand(toString(val));
        t.setCurrCommand("");
    }

    public String toString(double val){
        return instrName + " " + val + " = " + valueOfExecution;
    }
}
