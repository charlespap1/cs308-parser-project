package slogo.model.tokens;

/**
 * ClearScreen Instruction class.
 * @author Charles, Natalie, Michael
 */
public class ClearScreen extends DisplayCommand {
    private static final int numArgs = 0;

    /**
     * Constructs new ClearScreen.
     * @param name String name of command, used by toString
     */
    public ClearScreen(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Clears screen, moves all turtles to center by calling front end clear screen function.
     * @return 0 (result of executing this method)
     */
    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
