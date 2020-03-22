package slogo.model.tokens;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * SetPosition Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetPosition extends Instruction {

    private static final int numArgs = 2;
    private TurtleAction myAction = t -> {
        List<Double> paramsAsInts = getParamsAsVals();
        double xCord = paramsAsInts.get(0);
        double yCord = paramsAsInts.get(1);
        double returnValue = Point2D.distance(xCord, yCord, t.getXPos(), t.getYPos());
        t.setLocation(xCord, -yCord);
        return returnValue;
    };

    /**
     * Constructs new SetPosition.
     * @param name String name of command, used by toString
     */
    public SetPosition(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Moves all active turtles to given (x, y) coordinates.
     * @return amount last turtle moved to get to given position
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}
