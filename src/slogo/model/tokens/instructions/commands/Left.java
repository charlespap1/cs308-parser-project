package slogo.model.tokens.instructions.commands;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

import java.util.List;

public class Left extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double angle = t.getAngle() - valueForExec;
        t.setAngle(angle);
        return valueForExec;
    };

    public Left(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() { return myAccessor.turtleCommandToMaster(myAction); }
}
