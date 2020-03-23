package slogo.model.tokens;

/**
 * SetPenColor Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetPenColor extends DisplayCommand {
    private static final int numArgs = 1;

    /**
     * Constructs new SetPenColor.
     * @param name String name of command, used by toString
     */
    public SetPenColor(String name) {
        super(numArgs);
        instrName = name;
    }
}
