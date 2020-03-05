package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.List;

public class DoTimes extends Instruction {

    private static final int numArgs = 2;

    public DoTimes(String name){
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute () {
        // TODO: can this be put in instruction so it is reused?
        Token list1 = this.parameters.get(0);
        Token list2 = this.parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) { throw new InvalidArgumentException(); }
        double returnValue = 0;

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if (!(variable instanceof Variable)) throw new InvalidLoopConditionException();
        double limit = checkTokenNotListAndGetVal(loopParameters.get(1));

        List<Token> commands = ((ListSyntax) list2).getContents();
        for (int i = 1; i <= limit; i++) {
            ((Variable) variable).setVariable(i);
            for (Token command : commands) {
                if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
                returnValue = command.execute();
            }
        }
        return returnValue;
    }
}
