package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidCommandException;

import java.util.List;

/**
 * NewCommand Instruction class.
 * @author Charles, Natalie, Michael
 */
public class NewCommand extends Instruction {
    private String myName;
    private List<Token> myVariables;
    private List<Token> myInstructions;

    /**
     * Constructs new NewCommand.
     * @param name String name of command, used by toString
     * @param variables list of variables defined in this new command
     * @param instructions list of instructions run when this command is called
     */
    public NewCommand(String name, List<Token> variables, List<Token> instructions) {
        super(variables.size());
        myName = name;
        myVariables = variables;
        myInstructions = instructions;
    }

    /**
     * Gets name of new command.
     * @return name
     */
    public String getName() {
        return myName;
    }

    /**
     * Gets variables defined in new command.
     * @return list of variables
     */
    public List<Token> getVariables() {
        return myVariables;
    }

    /**
     * Gets instructions run in new command.
     * @return list of instructions
     */
    public List<Token> getInstructions() {
        return myInstructions;
    }

    /**
     * Sets variables to values given in parameters, then executes all instructions.
     * @return return value of last instruction executed
     */
    @Override
    public double execute() {
        for (int i = 0; i < parameters.size(); i++) {
            if (myVariables.get(i) instanceof Variable)
                ((Variable) myVariables.get(i)).setVariable(parameters.get(i).execute());
            else throw new InvalidArgumentException();
        }
        double returnValue = 0;
        for (Token instruction : myInstructions) {
            if (instruction instanceof Instruction) returnValue = instruction.execute();
            else throw new InvalidCommandException();
        }
        return returnValue;
    }

    /**
     * String representation of new command.
     * @return name of command
     */
    @Override
    public String toString() {
        return myName;
    }
}
