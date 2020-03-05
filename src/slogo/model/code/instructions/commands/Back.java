package slogo.model.code.instructions.commands;

import slogo.model.code.instructions.TurtleAction;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Back extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double x = t.getXPos() + valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() + valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        return valueForExec;
    };

    public Back(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleCommandToMaster(myAction); }

}
