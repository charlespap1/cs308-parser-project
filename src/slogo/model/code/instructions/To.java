package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;

import java.util.List;

public class To extends Instruction {
    private static final int NUM_ARGS = 3;

    @Override
    public void execute(Turtle turtle) {
        String name = parameters.get(0).toString();
        List<Token> variables = ((ListSyntax) parameters.get(1)).getContents();
        List<Token> instructions = ((ListSyntax) parameters.get(2)).getContents();

        NewCommand newCommand = new NewCommand(name, variables, instructions);
    }

    @Override
    public int numRequiredArgs() {
        return NUM_ARGS;
    }

    @Override
    public int generateValue() {
        return 0;
    }
}
