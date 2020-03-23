package slogo.model.tokens;

import java.util.List;

/**
 * Difference Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Difference extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Difference.
     * @param name String name of command, used by toString
     */
    public Difference(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return different between two parameters
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 - val2;
    }
}
