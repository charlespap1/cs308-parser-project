package slogo.model.tokens;

import java.util.List;

/**
 * Or Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Or extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Or.
     * @param name String name of command, used by toString
     */
    public Or(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return 1 if either parameter is not equal to 0, otherwise 0
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return (val1 != 0 || val2 != 0) ? 1 : 0;
    }
}
