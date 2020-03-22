package slogo.model.tokens;

/**
 * YCoordinate Instruction class.
 * @author Charles, Natalie, Michael
 */
public class YCoordinate extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> -t.getYPos();

    /**
     * Constructs new YCoordinate.
     * @param name String name of command, used by toString
     */
    public YCoordinate(String name) {
        super(numArgs);
        this.instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return y coordinate of turtle currently being queried
     */
    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }
}
