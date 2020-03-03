package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Left extends TurtleCommand {

    private static final int numArgs = 1;

    public Left(String name){
        super(numArgs);
        instrName = name;
    }

    protected void performAction (Turtle t) {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        valueOfExecution = valueForExec;
        double angle = t.getAngle() - valueForExec;
        t.setAngle(angle);
    }
}
