package slogo.model.tokens.instructions.misc;

import slogo.model.tokens.ListSyntax;
import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;
import slogo.model.tokens.instructions.Instruction;

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
        Token list2 = parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) throw new InvalidArgumentException();
        double returnValue = 0;

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if (!(variable instanceof Variable)) throw new InvalidLoopConditionException();

        double start = checkTokenNotListAndGetVal(loopParameters.get(1));
        double end = checkTokenNotListAndGetVal(loopParameters.get(2));
        double increment = checkTokenNotListAndGetVal(loopParameters.get(3));

        List<Token> commands = ((ListSyntax) list2).getContents();
        for (double i = start; i <= end; i += increment) {
            ((Variable) variable).setVariable(i);
            for (Token command: commands) {
                if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
                returnValue = command.execute();
            }
        }
        return returnValue;
    }

}
