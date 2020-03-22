package slogo.model.tokens;

import java.util.List;

/**
 * Random Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Random extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new Random.
     * @param name String name of command, used by toString
     */
    public Random(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return random value up to given max value
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double max = paramsAsVals.get(0);
        return Math.random() * max;
    }
}
