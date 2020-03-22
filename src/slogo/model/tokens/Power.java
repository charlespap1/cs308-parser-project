package slogo.model.tokens;

import java.util.List;

/**
 * Power Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Power extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Power.
     * @param name String name of command, used by toString
     */
    public Power(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return parameter 0 to the power of parameter 1
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double base = paramsAsVals.get(0);
        double exponent = paramsAsVals.get(1);
        return Math.pow(base, exponent);
    }
}
