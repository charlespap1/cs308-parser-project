package slogo.model.tokens;

import java.util.List;

/**
 * Sum Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Sum extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Sum.
     * @param name String name of command, used by toString
     */
    public Sum(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return sum of two parameters
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 + val2;
    }
}
