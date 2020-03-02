package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class DoTimes extends Instruction {

    private static final int numArgs = 2;

    public DoTimes(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void performAction (Turtle t) {
        Token list1 = this.parameters.get(0);
        Token list2 = this.parameters.get(1);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) {
            throw new InvalidArgumentException();
        }
        this.valueOfExecution = 0;

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if (!(variable instanceof Variable)) {
            throw new InvalidLoopConditionException();
        }
        double limit = checkTokenNotListAndGetVal(loopParameters.get(1), t);

        List<Token> commands = ((ListSyntax) list2).getContents();
        for (int i = 1; i <= limit; i++) {
            ((Variable) variable).setVariable(i);
            for (Token command : commands) {
                if (!(command instanceof Instruction)) {
                    throw new InvalidLoopConditionException();
                }
                ((Instruction) command).performAction(t);
                this.valueOfExecution = command.generateValue();
            }
        }
    }

    @Override
    public String toString(){ return instrName + ": "; }
}
