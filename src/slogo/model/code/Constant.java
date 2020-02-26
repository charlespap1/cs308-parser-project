package slogo.model.code;

public class Constant implements Token {

    private double val;

    public Constant(String value){
        val = Double.parseDouble(value);
        //TODO: error handling
    }

    public double generateValue() {
        return val;
    }

    public String toString() { return String.valueOf(val); }
}
