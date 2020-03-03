package slogo.model.code.instructions.math;

import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Log extends Instruction {

    private static final int numArgs = 1;

    public Log(String name) {
        super(numArgs);
        instrName = name;
    }

    public void execute () {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        valueOfExecution = Math.log(val);
    }

    public String toString(double val){
        return instrName + " " + val + " = " + valueOfExecution;
    }
}
