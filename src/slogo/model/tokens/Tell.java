package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

/**
 * Tell Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Tell extends Instruction {

    private static final int numArgs = 1;
    private List<Double> activeTurtles = new ArrayList<>();
    private TurtleAction myAction = t -> {
        if (activeTurtles.contains(t.getId())) {
            t.setActive(true);
            t.setVisible(true);
        } else t.setActive(false);
        return t.getId();
    };

    /**
     * Constructs new Tell.
     * @param name String name of command, used by toString
     */
    public Tell(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Sets turtles with IDs in list in parameter 0 to active, all else to inactive.
     * @return amount turtles are rotated
     */
    @Override
    public double execute() {
        Token list = parameters.get(0);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> turtles = ((ListSyntax) list).getContents();
        activeTurtles = getParamsAsVals(turtles);
        return myAccessor.multiTurtleCommandToMaster(myAction, activeTurtles);
    }
}
