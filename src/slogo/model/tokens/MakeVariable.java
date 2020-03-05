package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;

public class MakeVariable extends Instruction {

    private static final int numArgs = 2;

    public MakeVariable(String name){
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
