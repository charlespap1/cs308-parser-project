package slogo.model.tokens;

import slogo.model.exceptions.InvalidArgumentException;

/**
 * MakeVariable Instruction class.
 * @author Charles, Natalie, Michael
 */
public class MakeVariable extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new MakeVariable.
     * @param name String name of command, used by toString
     */
    public MakeVariable(String name) {
        super(numArgs);
        this.instrName = name;
    }

    /**
     * Sets value of given variable (parameter 0) to value in parameter 1.
     * @return value that variable is set to
     */
    @Override
    public double execute() {
        Token var = this.parameters.get(0);
        Token expr = this.parameters.get(1);
        if (!(var instanceof Variable)) throw new InvalidArgumentException();
        double val = checkTokenNotListAndGetVal(expr);
        ((Variable) var).setVariable(val);
        return val;
    }
}
