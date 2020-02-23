package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.Variable;

import java.util.List;

public class NewCommand extends Instruction {
    private static final int NUM_ARGS = 1;
    private String myName;
    private List<Token> myVariables;
    private List<Token> myInstructions;

    public NewCommand(String name, List<Token> variables, List<Token> instructions){
        myName = name;
        myVariables = variables;
        myInstructions = instructions;
    }

    public String getName() {
        return myName;
    }

    @Override
    public void execute(Turtle turtle) {
        if (parameters.get(0).generateValue() != myVariables.size()) throw new Error("wrong number of params");
        //TODO: error handling
        for (int i=0; i<parameters.size(); i++){
            if (myVariables.get(i) instanceof Variable)
                ((Variable) myVariables.get(i)).setVariable(parameters.get(i).generateValue());
        }
        for (Token instruction:myInstructions){
            if (instruction instanceof Instruction) ((Instruction) instruction).execute(turtle);
        }
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
