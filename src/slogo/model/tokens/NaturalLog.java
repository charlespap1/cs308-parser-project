package slogo.model.tokens;

import java.util.List;

/**
 * NaturalLog Instruction class.
 * @author Charles, Natalie, Michael
 */
public class NaturalLog extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new NaturalLog.
     * @param name String name of command, used by toString
     */
    public NaturalLog(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return natural log of given value
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return Math.log(val);
    }
}
