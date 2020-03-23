package slogo.model.tokens;

import slogo.model.Turtle;

/**
 * XCoordinate Instruction class.
 * @author Charles, Natalie, Michael
 */
public class XCoordinate extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = Turtle::getXPos;

    /**
     * Constructs new XCoordinate.
     * @param name String name of command, used by toString
     */
    public XCoordinate(String name) {
        super(numArgs);
        this.instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return x coordinate of turtle currently being queried
     */
    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }
}
