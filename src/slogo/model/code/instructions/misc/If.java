package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.CommandCannotDoListException;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class If extends Instruction {

    private static final int numArgs = 2;

    public If(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute () {
        Token expr = this.parameters.get(0);
        Token list = this.parameters.get(1);
        checkTokenNotListAndGetVal(expr);
        if (!(list instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        valueOfExecution = 0;
        if (expr.generateValue() != 0) {
            List<Token> commands = ((ListSyntax) list).getContents();
            for (Token command: commands) {
                if (!(command instanceof Instruction)) {
                    throw new InvalidLoopConditionException();
                }
                ((Instruction) command).execute();
                valueOfExecution = command.generateValue();
            }
        }
    }

    @Override
    public String toString() {
        return instrName;
    }
}
