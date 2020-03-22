package slogo.model.tokens;

import java.util.List;

/**
 * Not Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Not extends Instruction {

    private static final int numArgs = 1;

    /**
     * Constructs new Not.
     * @param name String name of command, used by toString
     */
    public Not(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return 1 if parameter equals 0, otherwise 0
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val = paramsAsVals.get(0);
        return (val == 0) ? 1 : 0;
    }
}
