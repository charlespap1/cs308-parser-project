package slogo.model.tokens;

/**
 * IsShowing Instruction class.
 * @author Charles, Natalie, Michael
 */
public class IsShowing extends Instruction {
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.isVisible() ? 1 : 0;

    /**
     * Constructs new IsShowing.
     * @param name String name of command, used by toString
     */
    public IsShowing(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return 1 if turtle currently being queried is visible, otherwise 0
     */
    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }

}
