package slogo.model.tokens;

import java.util.List;

/**
 * NotEqual Instruction class.
 * @author Charles, Natalie, Michael
 */
public class NotEqual extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new NotEqual.
     * @param name String name of command, used by toString
     */
    public NotEqual(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return 1 if parameter 0 is not equal to parameter 1, otherwise 0
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 != val2 ? 1 : 0;
    }
}
