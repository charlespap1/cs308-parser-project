package slogo.model.tokens;

/**
 * PenDown Instruction class.
 * @author Charles, Natalie, Michael
 */
public class PenDown extends DisplayCommand {
    private static final int numArgs = 0;

    /**
     * Constructs new PenDOwn.
     * @param name String name of command, used by toString
     */
    public PenDown(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality but calling front end function to put pen down.
     * @return 1
     */
    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
