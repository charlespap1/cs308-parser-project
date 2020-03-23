package slogo.model.tokens;

import java.util.List;

/**
 * Sine Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Sine extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new Sine.
     * @param name String name of command, used by toString
     */
    public Sine(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return sine of given parameter value
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.sin(Math.toRadians(val));
    }
}
