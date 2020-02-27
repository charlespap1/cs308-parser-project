package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.CommandCannotDoListException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class For extends Instruction {

    private static final int numArgs = 2;

    public For(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) throws InvalidLoopConditionException, CommandCannotDoListException {
        Token list1 = this.parameters.get(0);
        Token list2 = this.parameters.get(1);
        if(!(list1 instanceof ListSyntax) && !(list2 instanceof ListSyntax))
        {
            throw new InvalidLoopConditionException();
        }
        this.valueOfExecution = 0;

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if(!(variable instanceof Variable))
        {
            throw new InvalidLoopConditionException();
        }

        double start = checkTokenNotList(loopParameters.get(1), t);
        double end = checkTokenNotList(loopParameters.get(2), t);
        double increment = checkTokenNotList(loopParameters.get(3), t);
        t.setCurrCommand(toString());
        t.setCurrCommand("");

        List<Token> commands = ((ListSyntax) list2).getContents();
        for (double i = start; i <= end; i += increment) {
            ((Variable) variable).setVariable(i);
            for (Token command: commands) {
                if(!(command instanceof Instruction))
                {
                    throw new InvalidLoopConditionException();
                }
                ((Instruction) command).execute(t);
                this.valueOfExecution = command.generateValue();
            }
        }
    }

    @Override
    public String toString(){ return instrName + ": "; }

//    private double checkIfInt(Token currToken, Turtle t) throws CommandCannotDoListException
//    {
//        if(currToken instanceof ListSyntax)
//        {
//            throw new CommandCannotDoListException();
//        }
//        if (currToken instanceof Instruction){
//            ((Instruction) currToken).execute(t);
//        }
//
//        return currToken.generateValue();
//    }
}
