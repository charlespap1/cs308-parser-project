package slogo.model.tokens;

import java.util.List;

public class Forward extends Instruction {

    private static final int numArgs = 1;

    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double returnValue = paramsAsVals.get(0);
        double x = t.getXPos() - returnValue * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() - returnValue * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        return returnValue;
    };

    public Forward(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }
}
