package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Right extends Instruction {

    private static final int numArgs = 1;

    public Right(String name){
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = getParamsAsVals(t);
        double valueForExec = paramsAsVals.get(0);
        valueOfExecution = valueForExec;
        double angle = t.getAngle() + valueForExec;
        t.setAngle(angle);
    }
}