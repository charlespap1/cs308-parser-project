package slogo.model.code.instructions.misc;

import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class IfElse extends Instruction {

    private static final int numArgs = 3;

    public IfElse(String name){
        super(numArgs);
        this.instrName = name;
    }

    private double runCommandsInList (Token list) {
        if (!(list instanceof ListSyntax)) throw new InvalidArgumentException();
        List<Token> commands = ((ListSyntax) list).getContents();
        double returnValue = 0;
        for (Token command: commands) {
            if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
            returnValue = command.execute();
        }
        return returnValue;
    }

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
