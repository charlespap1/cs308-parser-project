package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.CommandCannotDoListException;
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

    public void execute () {
        Token expr = this.parameters.get(0);
        Token list1 = this.parameters.get(1);
        Token list2 = this.parameters.get(2);
        checkTokenNotListAndGetVal(expr);
        this.valueOfExecution = 0;
        if (expr.generateValue() != 0)
            runCommandsInList(list1);
        else
            runCommandsInList(list2);
    }

    private void runCommandsInList (Token list) {
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        List<Token> commands = ((ListSyntax) list).getContents();
        for (Token command: commands) {
            if (!(command instanceof Instruction)) {
                throw new InvalidLoopConditionException();
            }
            ((Instruction) command).execute();
            this.valueOfExecution = command.generateValue();
        }
    }

    @Override
    public String toString() { return instrName;}
}
