package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
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
        assert parameters.get(1) instanceof ListSyntax;
        assert parameters.get(2) instanceof ListSyntax;
        // TODO: error handling if not so
        List<Token> variables = ((ListSyntax) parameters.get(1)).getContents();
        List<Token> instructions = ((ListSyntax) parameters.get(2)).getContents();

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
