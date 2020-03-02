package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.exceptions.DivideByZeroException;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.SingleRunInstruction;

import java.util.List;

public class Quotient extends SingleRunInstruction {

    private static final int numArgs = 2;

    public Quotient(String name) {
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) throws DivideByZeroException {
        List<Double> paramsAsVals = this.getParamsAsVals(t);
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        if (val2 == 0 && val1 != 0) throw new DivideByZeroException();
        valueOfExecution = val1 / val2;
        t.setCurrCommand(toString(val1, val2));
        t.setCurrCommand("");
    }

    public String toString(double val1, double val2){
        return val1 + " / " + val2 + " = " + valueOfExecution;
    }
}
