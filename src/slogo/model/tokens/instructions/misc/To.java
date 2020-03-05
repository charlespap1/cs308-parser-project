package slogo.model.tokens.instructions.misc;

import slogo.model.tokens.ListSyntax;
import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;
import slogo.model.tokens.instructions.Instruction;
import slogo.model.tokens.instructions.NewCommand;
import slogo.model.parse.AddToListFunction;

import java.util.List;

public class To extends Instruction {
    private static final int numArgs = 3;
    private NewCommand myCommand;
    private AddToListFunction myFunction;

    public To(String name, AddToListFunction function){
        super(numArgs);
        instrName = name;
        myFunction = function;
    }

    @Override
    public double execute () {
        String name = parameters.get(0).toString();
        Token list1 = parameters.get(1);
        Token list2 = parameters.get(2);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) throw new InvalidArgumentException();

        List<Token> variables = ((ListSyntax) list1).getContents();
        for (Token variable : variables) if (!(variable instanceof Variable)) throw new InvalidLoopConditionException();

        List<Token> instructions = ((ListSyntax) list2).getContents();
        for (Token instruction : instructions) if (!(instruction instanceof Instruction)) throw new InvalidLoopConditionException();

        myCommand = new NewCommand(name, variables, instructions);
        myFunction.addToList(myCommand);
        return myCommand == null ? 0 : 1;
    }

    public String toString(String name){ return instrName + ": " + name; }
}
