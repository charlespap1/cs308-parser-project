package slogo.model.tokens;

/**
 * IsPenDown Instruction class.
 * @author Charles, Natalie, Michael
 */
public class IsPenDown extends DisplayCommand {
    private static final int numArgs = 0;

    /**
     * Constructs new IsPenDown.
     * @param name String name of command, used by toString
     */
    public IsPenDown(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality by calling front end function.
     * @return 0 if pen is up, 1 if pen is down
     */
    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
