package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Forward extends TurtleCommand {

    private static final int numArgs = 1;

    public Forward(String name){
        super(numArgs);
        instrName = name;
    }

    protected void performAction (Turtle t) {
        // TODO: change active turtle for query params
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        valueOfExecution = valueForExec;
        double x = t.getXPos() - valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() - valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
    }
}
