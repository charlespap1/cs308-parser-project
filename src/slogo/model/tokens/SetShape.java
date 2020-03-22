package slogo.model.tokens;

/**
 * SetShape Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetShape extends DisplayCommand {
    private static final int numArgs = 1;

    /**
     * Constructs new SetShape.
     * @param name String name of command, used by toString
     */
    public SetShape(String name) {
        super(numArgs);
        instrName = name;
    }
}
