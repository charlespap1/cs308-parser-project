package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.instructions.Instruction;

public class Make extends Instruction {

    private static final int numArgs = 2;

    public Make(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        Token var = this.parameters.get(0);
        Token expr = this.parameters.get(1);
        assert var instanceof Variable;
        // TODO: error handling if assertion fails
        assert !(expr instanceof ListSyntax);
        if (expr instanceof Instruction) {
            ((Instruction) expr).execute(t);
        }
        double val = expr.generateValue();
        ((Variable) var).setVariable(val);
        this.valueOfExecution = val;
        t.setCurrCommand(toString(var.toString(), val));
        t.setCurrCommand("");
    }

    public String toString(String var, double val){ return instrName + ": " + var + " " + val; }
}
