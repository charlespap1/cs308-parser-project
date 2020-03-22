package slogo.model.tokens;

/**
 * PenUp Instruction class.
 * @author Charles, Natalie, Michael
 */
public class PenUp extends DisplayCommand {
    private static final int numArgs = 0;

    /**
     * Constructs new PenUp.
     * @param name String name of command, used by toString
     */
    public PenUp(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality by calling front end function to put pen up.
     * @return 0
     */
    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
