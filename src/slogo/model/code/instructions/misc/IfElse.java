package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class IfElse extends Instruction {

    private static final int numArgs = 2;

    public IfElse(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        Token expr = this.parameters.get(0);
        if(expr instanceof Instruction)
            ((Instruction)expr).execute(t);
        Token list1 = this.parameters.get(1);
        Token list2 = this.parameters.get(2);
        // TODO error if assertion fails
        assert !(expr instanceof ListSyntax);
        this.valueOfExecution = 0;
        t.setCurrCommand(toString());
        t.setCurrCommand("");
        if (expr.generateValue() != 0)
            runCommandsInList(list1, t);
        else
            runCommandsInList(list2, t);
    }

    private void runCommandsInList (Token list, Turtle t) {
        assert list instanceof ListSyntax;
        List<Token> commands = ((ListSyntax) list).getContents();
        for (Token command: commands) {
            // TODO: error if assertion fails
            assert command instanceof Instruction;
            ((Instruction) command).execute(t);
            this.valueOfExecution = command.generateValue();
        }
    }

    public String toString() { return instrName;}
}
