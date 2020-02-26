package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Repeat extends Instruction {

    protected int NUM_ARGS = 2;

    public Repeat(String name){
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        Token expr = this.parameters.get(0);
        if(expr instanceof Instruction){
            ((Instruction)expr).execute(t);
        }
        int numRepeats = (int) expr.generateValue();
        // TODO: error if not integer value (ex: repeat + 1.5 2 [ insns ])
        Token list = this.parameters.get(1);
        List<Token> commands = ((ListSyntax) list).getContents();
        for (int i = 0; i < numRepeats; i++) {
            for (Token command : commands) {
                assert command instanceof Instruction;
                ((Instruction) command).execute(t);
                this.valueOfExecution = command.generateValue();
            }
        }
        t.setCurrCommand("Repeat ");
        t.setCurrCommand("");
        // TODO how are we doing history??
    }
}
