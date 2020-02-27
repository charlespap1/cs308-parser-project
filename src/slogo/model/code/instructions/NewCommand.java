package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.Variable;

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

    public void execute(Turtle turtle) {
        System.out.println(myVariables);
        System.out.println(myInstructions);
        System.out.println(parameters);
        for (int i=0; i<parameters.size(); i++){
            if (myVariables.get(i) instanceof Variable)
                ((Variable) myVariables.get(i)).setVariable(parameters.get(i).generateValue());
        }
        for (Token instruction:myInstructions){
            if (instruction instanceof Instruction) ((Instruction) instruction).execute(turtle);
        }
    }

    public double generateValue() {
        return 0;
    }
}
