package slogo.model.tokens;

public class Pi extends Instruction {

    private static final int numArgs = 0;

    public Pi(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return Math.PI;
    }
}
