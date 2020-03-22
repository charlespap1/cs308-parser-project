package slogo.model.tokens;

/**
 * SetPenSize Instruction class.
 * @author Charles, Natalie, Michael
 */
public class SetPenSize extends DisplayCommand {
    private static final int numArgs = 1;

    /**
     * Constructs new SetPenSize.
     * @param name String name of command, used by toString
     */
    public SetPenSize(String name) {
        super(numArgs);
        instrName = name;
    }
}
