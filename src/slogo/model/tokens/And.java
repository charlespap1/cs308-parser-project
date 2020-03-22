package slogo.model.tokens;

import java.util.List;

/**
 * And Instruction class.
 * @author Charles, Natalie, Michael
 */
public class And extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new And.
     * @param name String name of command, used by toString
     */
    public And(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return AND of two parameters
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return (val1 != 0 && val2 != 0) ? 1 : 0;
    }
}
