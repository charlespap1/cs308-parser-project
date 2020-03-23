package slogo.model.tokens;

/**
 * ShowTurtle Instruction class.
 * @author Charles, Natalie, Michael
 */
public class ShowTurtle extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        t.setVisible(true);
        return 1;
    };

    /**
     * Constructs new ShowTurtle.
     * @param name String name of command, used by toString
     */
    public ShowTurtle(String name) {
        super(numArgs);
        instrName = name;
    }

    /**
     * Executes instruction functionality. Sets all active turtles to visible.
     * @return 1
     */
    @Override
    public double execute() {
        return myAccessor.turtleCommandToMaster(myAction);
    }

}
