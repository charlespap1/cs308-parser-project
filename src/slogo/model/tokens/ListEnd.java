package slogo.model.tokens;

/**
 * ListEnd Token class to track the end of a user inputted list.
 * @author Charles, Natalie, Michael
 */
public class ListEnd implements Token {

    /**
     * Constructs new ListEnd.
     * @param name String name of command, used by toString
     */
    public ListEnd(String name) {
        super();
    }

    /**
     * Executes to 0 always.
     * @return 0
     */
    @Override
    public double execute() {
        return 0;
    }

    /**
     * String representation is end bracket.
     * @return end bracket
     */
    @Override
    public String toString() {
        return " ] ";
    }
}
