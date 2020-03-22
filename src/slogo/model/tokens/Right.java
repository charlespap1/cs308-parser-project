package slogo.model.tokens;

import java.util.List;

/**
 * Right Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Right extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double angle = t.getAngle() + valueForExec;
        t.setAngle(angle);
        return valueForExec;
    };

    /**
     * Constructs new Right.
     * @param name String name of command, used by toString
     */
    public Right(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Rotates all active turtles clockwise by given value
     * @return amount turtles are rotated
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}