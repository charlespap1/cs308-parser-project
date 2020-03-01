package slogo.model.code.instructions.misc;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.Variable;
import slogo.model.code.exceptions.InvalidArgumentException;
import slogo.model.code.exceptions.InvalidLoopConditionException;
import slogo.model.code.instructions.Instruction;

public class Make extends Instruction {

    private static final int numArgs = 2;

    public Make(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void performAction (Turtle t) {
        Token var = this.parameters.get(0);
        Token expr = this.parameters.get(1);
        if (!(var instanceof Variable)) {
            throw new InvalidArgumentException();
        }
        double val = checkTokenNotListAndGetVal(expr, t);
        ((Variable) var).setVariable(val);
        this.valueOfExecution = val;
        t.setCurrCommand(toString(var.toString(), val));
        t.setCurrCommand("");
    }

    public String toString(String var, double val){ return instrName + ": " + var + " " + val; }
}
