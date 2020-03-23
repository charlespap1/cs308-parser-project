package slogo.model.tokens;

import java.util.List;

/**
 * ArcTangent Instruction class.
 * @author Charles, Natalie, Michael
 */
public class ArcTangent extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new And.
     * @param name String name of command, used by toString
     */
    public ArcTangent(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return arctan of one parameter
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.atan(val);
    }
}
