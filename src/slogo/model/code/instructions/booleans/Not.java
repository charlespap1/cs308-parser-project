package slogo.model.code.instructions.booleans;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Not extends Instruction {

    private static final int numArgs = 1;

    public Not(String name) {
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = this.getParamsAsVals(t);
        // TODO: check type
        double val = paramsAsInts.get(0);
        this.valueOfExecution = (val == 0) ? 1 : 0;
        t.setCurrCommand(toString(val));
        t.setCurrCommand("");
    }

    public String toString(double val){
        return instrName + val + " = " + valueOfExecution;
    }
}
