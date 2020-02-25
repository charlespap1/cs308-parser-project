package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.Instruction;

public class Make extends Instruction {

    private static final int NUM_ARGS = 2;

    public Make(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token var = this.parameters.get(0);
        Token expr = this.parameters.get(1);
        assert var instanceof Variable;
        assert !(expr instanceof ListSyntax);
        if (expr instanceof Instruction) {
            ((Instruction) expr).execute(t);
        }
        int val = expr.generateValue();
        ((Variable) var).setVariable(val);
        this.valueOfExecution = val;
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
