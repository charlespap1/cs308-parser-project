package slogo.model.tokens.instructions.commands;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

import java.util.List;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double returnValue = Math.abs(t.getAngle() - valueForExec);
        t.setAngle(valueForExec);
        return returnValue;
    };

    public SetHeading(String name){
        super(numArgs);
        instrName = name;
    }

    public String toString(double heading){
        return instrName + " " + heading;
    }

    @Override
    public double execute(){ return myAccessor.turtleCommandToMaster(myAction); }

}
