package slogo.model.tokens;

/**
 * Constant Token class.
 * @author Charles, Natalie, Michael
 */
public class Constant implements Token {

    private double val;

    /**
     * Constructs new Constant.
     * @param value String version of value of Constant
     */
    public Constant(String value) {
        val = Double.parseDouble(value);
    }

    /**
     * Gives value of constant.
     * @return value
     */
    public double execute() {
        return val;
    }

    /**
     * Get String representation of Constant.
     * @return value
     */
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
