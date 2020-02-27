package slogo.model.code.instructions.misc;

import java.util.ArrayList;
import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.CommandCantDoListException;
import slogo.model.code.exceptions.InvalidLoopCondtionException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class For extends Instruction {

    private static final int numArgs = 2;

    public For(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) throws InvalidLoopCondtionException, CommandCantDoListException {
        Token list1 = parameters.get(0);
        Token list2 = parameters.get(1);
        if(!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) throw new InvalidLoopCondtionException();
        valueOfExecution = 0;

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        if(!(variable instanceof Variable)) throw new InvalidLoopCondtionException();

        double start = getVal(loopParameters.get(1), t);
        double end = getVal(loopParameters.get(2), t);
        double increment = getVal(loopParameters.get(3), t);
        t.setCurrCommand(toString());
        t.setCurrCommand("");

        List<Token> commands = ((ListSyntax) list2).getContents();
        for (double i = start; i <= end; i += increment) {
            ((Variable) variable).setVariable(i);
            for (Token command: commands) {
                if(!(command instanceof Instruction)) throw new InvalidLoopCondtionException();
                ((Instruction) command).execute(t);
                valueOfExecution = command.generateValue();
            }
        }
    }

    @Override
    public String toString(){ return instrName + ": "; }

    private double getVal(Token currToken, Turtle t) throws CommandCantDoListException {
        if(currToken instanceof ListSyntax) throw new CommandCantDoListException();
        else if (currToken instanceof Instruction) ((Instruction) currToken).execute(t);
        return currToken.generateValue();
    }
}
