package slogo.model.tokens;

import java.util.List;

/**
 * GreaterThan Instruction class.
 * @author Charles, Natalie, Michael
 */
public class GreaterThan extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new GreaterThan.
     * @param name String name of command, used by toString
     */
    public GreaterThan(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return 1 if parameter 0 > parameter 1, otherwise 0
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 > val2 ? 1 : 0;
    }
}
