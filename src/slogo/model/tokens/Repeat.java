package slogo.model.tokens;

public class Repeat extends Instruction {

    private static final int numArgs = 2;

    public Repeat(String name){
        super(numArgs);
        this.instrName = name;
    }

    @Override
    public double execute () {
        Token expr = this.parameters.get(0);
        double numRepeats = checkTokenNotListAndGetVal(expr);
        return runLoop(1, numRepeats, 1, null);
    }
}
