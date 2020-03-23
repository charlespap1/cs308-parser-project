package slogo.model.tokens;

/**
 * GetShape DisplayCommand class.
 * @author Charles, Natalie, Michael
 */
public class GetShape extends DisplayCommand {
    private static final int numArgs = 0;

    /**
     * Constructs new GetShape.
     * @param name String name of command, used by toString
     */
    public GetShape(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Gets current turtle shape, calling front end function.
     * @return palette ID number associated with current turtle shape
     */
    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
