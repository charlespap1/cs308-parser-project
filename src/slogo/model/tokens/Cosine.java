package slogo.model.tokens;

import java.util.List;

/**
 * Cosine Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Cosine extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new Cosine.
     * @param name String name of command, used by toString
     */
    public Cosine(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return cosine of one parameter
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.cos(Math.toRadians(val));
    }
}
