package slogo.model.tokens;

/**
 * SetBackground Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetBackground extends DisplayCommand {
    private static final int numArgs = 1;

    /**
     * Constructs new SetBackground.
     * @param name String name of command, used by toString
     */
    public SetBackground(String name) {
        super(numArgs);
        instrName = name;
    }
}
