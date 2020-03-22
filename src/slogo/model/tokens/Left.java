package slogo.model.tokens;

import java.util.List;

/**
 * Left Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Left extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double angle = t.getAngle() - valueForExec;
        t.setAngle(angle);
        return valueForExec;
    };

    /**
     * Constructs new Left.
     * @param name String name of command, used by toString
     */
    public Left(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Rotates all active turtles counterclockwise by amount in parameter.
     * @return amount turtles rotate
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }
}
