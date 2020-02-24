package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class If extends Instruction {

    private static final int NUM_ARGS = 2;

    public If(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token expr = this.parameters.get(0);
        Token list = this.parameters.get(1);
        assert !(expr instanceof ListSyntax);
        assert list instanceof ListSyntax;
        this.valueOfExecution = 0;
        if (expr.generateValue() != 0) {
            List<Token> commands = ((ListSyntax) list).getContents();
            for (Token command: commands) {
                assert command instanceof Instruction;
                ((Instruction) command).execute(t);
                this.valueOfExecution = command.generateValue();
            }
        }
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
