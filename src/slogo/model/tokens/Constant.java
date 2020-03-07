package slogo.model.tokens;

public class Constant implements Token {

    private double val;

    public Constant(String value) {
        val = Double.parseDouble(value);
    }

    public double execute() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
