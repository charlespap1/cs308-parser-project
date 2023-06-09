package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.List;

/**
 * DoTimes Instruction class.
 * @author Charles, Natalie, Michael
 */
public class DoTimes extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new DoTimes.
     * @param name String name of command, used by toString
     */
    public DoTimes(String name) {
        super(numArgs);
        this.instrName = name;
    }

    /**
     * Sets up for-loop to run for variable value of 1 through limit, incrementing by 1.
     * @return result of executing last command on last turtle
     */
    @Override
    public double execute() {
        Token list1 = this.parameters.get(0);
        Token list2 = this.parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if (!(variable instanceof Variable)) throw new InvalidLoopConditionException();
        double limit = checkTokenNotListAndGetVal(loopParameters.get(1));

        return runLoop(1, limit, 1, variable);
    }

}
