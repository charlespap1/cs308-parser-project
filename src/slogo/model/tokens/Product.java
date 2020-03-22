package slogo.model.tokens;

import java.util.List;

/**
 * Product Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Product extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Product.
     * @param name String name of command, used by toString
     */
    public Product(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return parameter 0 times parameter 1
     */
    @Override
    public double execute() {
        List<Double> paramsAsVals = this.getParamsAsVals();
        double val1 = paramsAsVals.get(0);
        double val2 = paramsAsVals.get(1);
        return val1 * val2;
    }
}
