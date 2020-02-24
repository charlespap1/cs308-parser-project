package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Repeat extends Instruction {

    private static final int NUM_ARGS = 2;

    public Repeat(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsVals = getParamsAsVals(t);
        int numRepeats = paramsAsVals.get(0);
        Token list = this.parameters.get(1);
        if (list instanceof ListSyntax) {
            List<Token> commands = ((ListSyntax) list).getContents();
            for (int i = 0; i < numRepeats; i++) {
                for (Token command : commands) {
                    if (command instanceof Instruction) {
                        ((Instruction) command).execute(t);
                    }
                    this.valueOfExecution = command.generateValue();
                }
            }
        } else {
            //throw error
        }
        t.setCurrCommand("Repeat ");
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName;
    }
}
