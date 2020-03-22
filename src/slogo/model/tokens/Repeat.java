package slogo.model.tokens;

/**
 * Repeat Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Repeat extends Instruction {

    private static final int numArgs = 2;

    /**
     * Constructs new Repeat.
     * @param name String name of command, used by toString
     */
    public Repeat(String name) {
        super(numArgs);
        this.instrName = name;
    }

    /**
     * Sets up loop to run numRepeats times.
     * @return amount last turtle acted on moves backward
     */
    @Override
    public double execute() {
        Token expr = this.parameters.get(0);
        double numRepeats = checkTokenNotListAndGetVal(expr);
        return runLoop(1, numRepeats, 1, null);
    }
}
