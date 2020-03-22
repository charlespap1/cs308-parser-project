package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.List;

/**
 * IfElse Instruction class.
 * @author Charles, Natalie, Michael
 */
public class IfElse extends Instruction {

    private static final int numArgs = 3;

    /**
     * Constructs new IfElse.
     * @param name String name of command, used by toString
     */
    public IfElse(String name) {
        super(numArgs);
        this.instrName = name;
    }

    private double runCommandsInList(Token list) {
        if (!(list instanceof ListSyntax)) throw new InvalidArgumentException();
        List<Token> commands = ((ListSyntax) list).getContents();
        double returnValue = 0;
        for (Token command : commands) {
            if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
            returnValue = command.execute();
        }
        return returnValue;
    }

    /**
     * Executes instruction functionality. Executes commands in list 1 (parameter 1) if condition in
     * parameter 0 is not false (0), otherwise executes commands in list 2 (parameter 2).
     * @return value of last command executed on last turtle
     */
    @Override
    public double execute() {
        Token expr = this.parameters.get(0);
        Token list1 = this.parameters.get(1);
        Token list2 = this.parameters.get(2);
        checkTokenNotListAndGetVal(expr);
        double returnValue = 0;
        if (expr.execute() != 0)
            returnValue = runCommandsInList(list1);
        else
            returnValue = runCommandsInList(list2);
        return returnValue;
    }
}
