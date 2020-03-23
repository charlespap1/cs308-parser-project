package slogo.model.tokens;

import java.util.List;

/**
 * Tangent Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Tangent extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new Tangent.
     * @param name String name of command, used by toString
     */
    public Tangent(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return tangent of given parameter value
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.tan(Math.toRadians(val));
    }
}
