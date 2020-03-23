package slogo.model.tokens;

import slogo.model.Turtle;

/**
 * ID Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Id extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = Turtle::getId;

    /**
     * Constructs new ID.
     * @param name String name of command, used by toString
     */
    public Id(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return ID of turtle currently being queried
     */
    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }

}
