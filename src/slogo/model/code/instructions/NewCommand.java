package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidCommandException;

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

    public void performAction(Turtle turtle) {
        for (int i=0; i<parameters.size(); i++){
            if (myVariables.get(i) instanceof Variable)
                ((Variable) myVariables.get(i)).setVariable(parameters.get(i).generateValue());
            else throw new InvalidArgumentException();
        }
        for (Token instruction:myInstructions){
            if (instruction instanceof Instruction) ((Instruction) instruction).performAction(turtle);
            else throw new InvalidCommandException();
        }
    }

    public double generateValue() {
        return 0;
    }
}
