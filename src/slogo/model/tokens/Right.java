package slogo.model.tokens;

import java.util.List;

public class Right extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double angle = t.getAngle() + valueForExec;
        t.setAngle(angle);
        return valueForExec;
    };

    public Right(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}