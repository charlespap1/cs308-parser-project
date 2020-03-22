package slogo.model.tokens;

import java.util.List;

/**
 * Equal Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Equal extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Equal.
     * @param name String name of command, used by toString
     */
    public Equal(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return 1 if two parameters are equal, 0 otherwise
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 == val2 ? 1 : 0;
    }
}
