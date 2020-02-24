package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;
import slogo.model.code.instructions.NewCommand;
import slogo.model.parse.AddToListFunction;

import java.util.List;

public class To extends Instruction {
    private static final int NUM_ARGS = 3;
    private NewCommand myCommand;
    private AddToListFunction myFunction;

    public To(AddToListFunction function){
        myFunction = function;
    }

    @Override
    public void execute(Turtle turtle) {
        String name = parameters.get(0).toString();
        List<Token> variables = ((ListSyntax) parameters.get(1)).getContents();
        List<Token> instructions = ((ListSyntax) parameters.get(2)).getContents();

        myCommand = new NewCommand(name, variables, instructions);
        myFunction.addToList(myCommand);
    }

    @Override
    public int numRequiredArgs() {
        return NUM_ARGS;
    }

    @Override
    public int generateValue() {
        if (myCommand != null) return 1;
        return 0;
    }
}
