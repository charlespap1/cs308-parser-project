package slogo.model.tokens.instructions.misc;

import slogo.model.tokens.Token;
import slogo.model.tokens.Variable;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.tokens.instructions.Instruction;

public class Make extends Instruction {

    private static final int numArgs = 2;

    public Make(String name){
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute () {
        Token var = this.parameters.get(0);
        Token expr = this.parameters.get(1);
        if (!(var instanceof Variable)) throw new InvalidArgumentException();
        double val = checkTokenNotListAndGetVal(expr);
        ((Variable) var).setVariable(val);
        return val;
    }
}
