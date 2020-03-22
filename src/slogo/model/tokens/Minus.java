package slogo.model.tokens;

import java.util.List;

/**
 * Minus Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Minus extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new Minus.
     * @param name String name of command, used by toString
     */
    public Minus(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return negated version of given value
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return -val;
    }
}
