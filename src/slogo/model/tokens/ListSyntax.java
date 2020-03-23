package slogo.model.tokens;

import java.util.List;

/**
 * ListSyntax Token class.
 * @author Charles, Natalie, Michael
 */
public class ListSyntax implements Token {
    private List<Token> contents;

    /**
     * Constructs new ListSyntax.
     * @param stuff contents of this list
     */
    public ListSyntax(List<Token> stuff) {
        contents = stuff;
    }

    /**
     * Executes list.
     * @return number of items in list.
     */
    public double execute() {
        return contents.size();
    }

    /**
     * Getter for list contents.
     * @return contents
     */
    public List<Token> getContents() {
        return contents;
    }

    /**
     * String representation of list.
     * @return String of list contents, surrounded by open/close brackets
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Token t : contents) sb.append(" ").append(t.toString());
        sb.append(" ]");
        return sb.toString();
    }
}
