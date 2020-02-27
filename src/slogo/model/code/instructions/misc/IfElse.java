package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.CommandCannotDoListException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class IfElse extends Instruction {

    private static final int numArgs = 2;

    public IfElse(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        Token expr = this.parameters.get(0);
        Token list1 = this.parameters.get(1);
        Token list2 = this.parameters.get(2);
        checkTokenNotListAndGetVal(expr, t);
        this.valueOfExecution = 0;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
        if (expr.generateValue() != 0)
            runCommandsInList(list1, t);
        else
            runCommandsInList(list2, t);
    }

    private void runCommandsInList (Token list, Turtle t) {
        if (!(list instanceof ListSyntax)) {
            throw new InvalidLoopConditionException();
        }
        List<Token> commands = ((ListSyntax) list).getContents();
        for (Token command: commands) {
            if (!(command instanceof Instruction)) {
                throw new InvalidLoopConditionException();
            }
            ((Instruction) command).execute(t);
            this.valueOfExecution = command.generateValue();
        }
    }

    @Override
    public String toString() { return instrName;}
}
