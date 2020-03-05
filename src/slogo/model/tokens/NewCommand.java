package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidCommandException;

import java.util.List;

public class NewCommand extends Instruction {
    private String myName;
    private List<Token> myVariables;
    private List<Token> myInstructions;

    public NewCommand(String name, List<Token> variables, List<Token> instructions){
        super(variables.size());
        myName = name;
        myVariables = variables;
        myInstructions = instructions;
    }

    public String getName() {
        return myName;
    }

    @Override
    public double execute() {
        for (int i=0; i<parameters.size(); i++){
            if (myVariables.get(i) instanceof Variable)
                ((Variable) myVariables.get(i)).setVariable(parameters.get(i).execute());
            else throw new InvalidArgumentException();
        }
        double returnValue = 0;
        for (Token instruction:myInstructions){
            if (instruction instanceof Instruction) returnValue = instruction.execute();
            else throw new InvalidCommandException();
        }
        return returnValue;
    }
}
