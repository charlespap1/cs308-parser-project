package slogo.model.tokens.instructions.misc;

import slogo.model.tokens.ListSyntax;
import slogo.model.tokens.Token;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;
import slogo.model.tokens.instructions.Instruction;

import java.util.List;

public class Repeat extends Instruction {

    private static final int numArgs = 2;

    public Repeat(String name){
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute () {
        Token expr = this.parameters.get(0);
        double numRepeats = checkTokenNotListAndGetVal(expr);
        Token list = this.parameters.get(1);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }

        double returnValue = 0;

        List<Token> commands = ((ListSyntax) list).getContents();
        for (int i = 0; i < numRepeats; i++) {
            for (Token command : commands) {
                if (!(command instanceof Instruction)) { throw new InvalidLoopConditionException(); }
                returnValue = command.execute();
            }
        }
        return returnValue;
    }
}
