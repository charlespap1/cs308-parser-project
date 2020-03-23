package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;
import slogo.model.parse.AddToListFunction;

import java.util.List;

/**
 * MakeUserInstruction Instruction class.
 * @author Charles, Natalie, Michael
 */
public class MakeUserInstruction extends Instruction {
    private static final int numArgs = 3;
    private NewCommand myCommand;
    private AddToListFunction myFunction;

    /**
     * Constructs new MakeUserInstruction.
     * @param name String name of command, used by toString
     * @param function method to add new command to the new commands list
     */
    public MakeUserInstruction(String name, AddToListFunction function) {
        super(numArgs);
        instrName = name;
        myFunction = function;
    }

    /**
     * Builds a new command using parameter 0 as the name, parameter 1 as the list of variables defined, and
     * parameter 2 as the list of commands.
     * @return 1 if new command is valid, otherwise 0
     */
    @Override
    public double execute() {
        String name = parameters.get(0).toString();
        Token list1 = parameters.get(1);
        Token list2 = parameters.get(2);
        if (!(list1 instanceof ListSyntax) || !(list2 instanceof ListSyntax)) throw new InvalidArgumentException();

        List<Token> variables = ((ListSyntax) list1).getContents();
        for (Token variable : variables) if (!(variable instanceof Variable)) throw new InvalidLoopConditionException();

        List<Token> instructions = ((ListSyntax) list2).getContents();
        for (Token instruction : instructions)
            if (!(instruction instanceof Instruction)) throw new InvalidLoopConditionException();

        myCommand = new NewCommand(name, variables, instructions);
        myFunction.addToList(myCommand);
        return myCommand == null ? 0 : 1;
    }
}
