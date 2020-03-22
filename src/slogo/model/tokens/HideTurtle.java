package slogo.model.tokens;

/**
 * HideTurtle Instruction class.
 * @author Charles, Natalie, Michael
 */
public class HideTurtle extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        t.setVisible(false);
        return 0;
    };

    /**
     * Constructs new HideTurtle.
     * @param name String name of command, used by toString
     */
    public HideTurtle(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Hides all active turtles.
     * @return 0
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

    @Override
    public String toString() {
        return instrName;
    }
}
