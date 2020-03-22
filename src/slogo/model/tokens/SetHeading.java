package slogo.model.tokens;

import java.util.List;

/**
 * SetHeading Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetHeading extends Instruction {
    private static final int ANGLE_OFFSET = 90;

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double returnValue = Math.abs(t.getAngle() - valueForExec);
        t.setAngle(valueForExec + ANGLE_OFFSET);
        return returnValue;
    };

    /**
     * Constructs new SetHeading.
     * @param name String name of command, used by toString
     */
    public SetHeading(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Sets angle of all active turtles to given angle.
     * @return amount last turtle rotates to reach given angle
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}
