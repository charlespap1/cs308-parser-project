package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class For extends Instruction {

    private static final int NUM_ARGS = 2;

    public For(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token list1 = this.parameters.get(0);
        Token list2 = this.parameters.get(1);
        this.valueOfExecution = 0;
        if (list1 instanceof ListSyntax) {
            List<Token> loopParameters = ((ListSyntax) list1).getContents();
            Token variable = loopParameters.get(0);
            int start = loopParameters.get(1).generateValue();
            int end = loopParameters.get(2).generateValue();
            int increment = loopParameters.get(3).generateValue();
            if (list2 instanceof ListSyntax) {
                List<Token> commands = ((ListSyntax) list2).getContents();
                for (int i = start; i <= end; i += increment) {
                    if (variable instanceof Variable) {
                        ((Variable) variable).setVariable(i);
                    }
                    for (Token command: commands) {
                        if (command instanceof Instruction) {
                            ((Instruction) command).execute(t);
                        }
                        this.valueOfExecution = command.generateValue();
                    }
                }
            }
        } else {
            //throw error
        }
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName;
    }
}
