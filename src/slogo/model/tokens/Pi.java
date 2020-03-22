package slogo.model.tokens;

/**
 * Pi Instruction class.
 * @author Charles, Natalie, Michael
 */
public class Pi extends Instruction {

    private static final int numArgs = 0;

    /**
     * Constructs new Pi.
     * @param name String name of command, used by toString
     */
    public Pi(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality.
     * @return Pi
     */
    @Override
    public double execute() {
        return Math.PI;
    }
}
