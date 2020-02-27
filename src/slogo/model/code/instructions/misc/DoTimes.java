package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class DoTimes extends Instruction {

    private static final int numArgs = 2;

    public DoTimes(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        Token list1 = this.parameters.get(0);
        Token list2 = this.parameters.get(1);
        // TODO: pass error back to model
        assert (list1 instanceof ListSyntax) : "First parameter of this instruction needs to be a list";
        assert (list2 instanceof ListSyntax) : "Second parameter of this instruction needs to be a list";
        this.valueOfExecution = 0;

        List<Token> loopParameters = ((ListSyntax) list1).getContents();
        Token variable = loopParameters.get(0);
        assert variable instanceof Variable;
        // need to error check this? if so, check limit is not a list. also, if it's an instruction, need to execute
        // TODO: error if cannot be resolved to an int
        int limit = (int) loopParameters.get(1).generateValue();

        t.setCurrCommand(toString());
        t.setCurrCommand("");

        List<Token> commands = ((ListSyntax) list2).getContents();
        for (int i = 1; i <= limit; i++) {
            ((Variable) variable).setVariable(i);
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
