package slogo.model.tokens;

/**
 * GetPenColor DisplayCommand class.
 * @author Charles, Natalie, Michael
 */
public class GetPenColor extends DisplayCommand {
    private static final int numArgs = 0;

    /**
     * Constructs new GetPenColor.
     * @param name String name of command, used by toString
     */
    public GetPenColor(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Gets current pen color, calling front end function.
     * @return palette ID number associated with current pen color
     */
    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
