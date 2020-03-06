package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.List;

public class For extends Instruction {

    private static final int numArgs = 2;

    public For(String name){
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute () {
        Token list1 = parameters.get(0);
        if (!(list1 instanceof ListSyntax)) throw new InvalidArgumentException();

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if (!(variable instanceof Variable)) throw new InvalidLoopConditionException();

        double start = checkTokenNotListAndGetVal(loopParameters.get(1));
        double end = checkTokenNotListAndGetVal(loopParameters.get(2));
        double increment = checkTokenNotListAndGetVal(loopParameters.get(3));

        return runLoop(start, end, increment, variable);
    }
}
