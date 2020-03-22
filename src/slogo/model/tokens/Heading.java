package slogo.model.tokens;

/**
 * Heading Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Heading extends Instruction {
    public static final int ANGLE_OFFSET = 90;

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.getAngle() - ANGLE_OFFSET;

    /**
     * Constructs new Heading.
     * @param name String name of command, used by toString
     */
    public Heading(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return angle of turtle currently being queried
     */
    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }

}
