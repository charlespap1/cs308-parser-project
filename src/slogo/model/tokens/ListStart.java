package slogo.model.tokens;

/**
 * ListStart Instruction class. Only will be seen as "complete" when parser comes across ListEnd Token.
 * @author Charles, Natalie, Michael
 */
public class ListStart extends Instruction {

    private static final int numArgs = -1;

    /**
     * Constructs new ListStart.
     * @param name String name of command, used by toString
     */
    public ListStart(String name) {
        super(numArgs);
    }

    /**
     * Executes to -1 always.
     * @return -1
     */
    public double execute() {
        return -1;
    }

    /**
     * String representation is start bracket.
     * @return start bracket
     */
    @Override
    public String toString() {
        return " [ ";
    }
}
