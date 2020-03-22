package slogo.model.tokens;

import java.util.List;

/**
 * Back Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Backward extends Instruction {

    private static final int numArgs = 1;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double x = t.getXPos() + valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() + valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        return valueForExec;
    };

    /**
     * Constructs new Backward.
     * @param name String name of command, used by toString
     */
    public Backward(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Moves all active turtles backward by amount in parameter.
     * @return amount last turtle acted on moves backward
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}
