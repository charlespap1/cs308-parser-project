package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Repeat extends Instruction {

    private static final int numArgs = 2;

    public Repeat(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        Token expr = this.parameters.get(0);
        if(expr instanceof Instruction){
            ((Instruction)expr).execute(t);
        }
        int numRepeats = (int) expr.generateValue();
        // TODO: error if not given a list
        Token list = this.parameters.get(1);
        t.setCurrCommand(toString());
        t.setCurrCommand("");

        List<Token> commands = ((ListSyntax) list).getContents();
        for (int i = 0; i < numRepeats; i++) {
            for (Token command : commands) {
                assert command instanceof Instruction;
                ((Instruction) command).execute(t);
                this.valueOfExecution = command.generateValue();
            }
        }
    }

    @Override
    public String toString(){ return instrName + ": "; }
}
