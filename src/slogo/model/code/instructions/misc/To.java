package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.NewCommand;
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

    public void execute(Turtle turtle) {
        String name = parameters.get(0).toString();
        Token list1 = parameters.get(1);
        Token list2 = parameters.get(2);
        if (!(list1 instanceof ListSyntax) && !(list2 instanceof ListSyntax)) {
            throw new InvalidLoopConditionException();
        }
        List<Token> variables = ((ListSyntax) list1).getContents();
        List<Token> instructions = ((ListSyntax) list2).getContents();
        myCommand = new NewCommand(name, variables, instructions);
        myFunction.addToList(myCommand);
        turtle.setCurrCommand(toString(name));
        turtle.setCurrCommand("");
    }

    public double generateValue() {
        if (myCommand != null) return 1;
        return 0;
    }

    public String toString(String name){ return instrName + ": " + name; }
}
