package slogo.model.tokens;

import java.util.ArrayList;

/**
 * Turtles Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Turtles extends Instruction {

    private static final int numArgs = 0;
    private int numTurtles;
    private TurtleAction myAction = t -> {
        numTurtles++;
        return numTurtles;
    };

    /**
     * Constructs new Turtles.
     * @param name String name of command, used by toString
     */
    public Turtles(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Counts number of turtles created so far.
     * @return number of turtles
     */
    @Override
    public double execute() {
        numTurtles = 0;
        myAccessor.multiTurtleCommandToMaster(myAction, new ArrayList<>());
        return numTurtles;
    }
}
