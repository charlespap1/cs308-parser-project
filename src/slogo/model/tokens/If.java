package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.List;

public class If extends Instruction {

    private static final int numArgs = 2;

    public If(String name) {
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute() {
        Token expr = this.parameters.get(0);
        Token list = this.parameters.get(1);
        checkTokenNotListAndGetVal(expr);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        double returnValue = 0;
        if (expr.execute() != 0) {
            List<Token> commands = ((ListSyntax) list).getContents();
            for (Token command : commands) {
                if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
                returnValue = command.execute();
            }
        }
        return returnValue;
    }
}
