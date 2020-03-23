package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.List;

/**
 * Ask Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Ask extends Instruction {

    private static final int numArgs = 2;
    private List<Double> activeTurtles;
    private List<Token> commands;

    private TurtleAction myAction = t -> {
        double returnValue = 0;
        if (activeTurtles.contains(t.getId())) {
            for (Token command : commands) {
                if (!(command instanceof Instruction)) {
                    throw new InvalidLoopConditionException();
                }
                returnValue = command.execute();
            }
        }
        return returnValue;
    };

    /**
     * Constructs new Ask.
     * @param name String name of command, used by toString
     */
    public Ask(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. First parameter is list of Turtle IDs to act on, second parameter is
     * list of commands to execute on these Turtles. Returns result of executing last command on last turtle executed on.
     * @return last return value from last turtle executed on
     */
    @Override
    public double execute() {
        Token list1 = parameters.get(0);
        if (!(list1 instanceof ListSyntax)) throw new InvalidArgumentException();
        List<Token> turtles = ((ListSyntax) list1).getContents();
        activeTurtles = getParamsAsVals(turtles);

        Token list2 = parameters.get(1);
        if (!(list2 instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        commands = ((ListSyntax) list2).getContents();

        return myAccessor.multiTurtleCommandToMaster(myAction, activeTurtles);
    }
}
