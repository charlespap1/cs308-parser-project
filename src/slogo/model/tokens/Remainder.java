package slogo.model.tokens;

import java.util.List;

/**
 * Remainder Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Remainder extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Remainder.
     * @param name String name of command, used by toString
     */
    public Remainder(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return remainder of dividing parameter 0 by parameter 1
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 % val2;
    }
}
