package slogo.model.tokens;

import java.util.List;

public class SetTowards extends Instruction {
    private static final int ANGLE_OFFSET = 90;
    private static final int numArgs = 2;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double xCord = paramsAsVals.get(0);
        double yCord = paramsAsVals.get(1);
        double angle = Math.atan2(xCord - t.getXPos(), yCord - t.getYPos());
        double angleDegrees = Math.toDegrees(angle);
        double returnValue = Math.abs(t.getAngle() - angleDegrees);
        t.setAngle(angleDegrees + ANGLE_OFFSET);
        return returnValue;
    };

    public SetTowards(String name) {
        super(numArgs);
        instrName = name;
    }

    public String toString(double x, double y) {
        return instrName + " " + x + " " + y;
    }

    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}
