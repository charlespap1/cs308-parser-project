package slogo.model.tokens;

/**
 * NewCommandName Token class. Used to store the name of a new command in the arguments stack while parsing.
 * @author Charles, Natalie, Michael
 */
public class NewCommandName implements Token {
    private String myName;

    /**
     * Constructs new NewCommandName.
     * @param name String name of command, used by toString
     */
    public NewCommandName(String name) {
        myName = name;
    }

    /**
     * Executes to 0.
     * @return 0
     */
    @Override
    public double execute() {
        return 0;
    }

    /**
     * String representation.
     * @return name
     */
    @Override
    public String toString() {
        return myName;
    }
}
