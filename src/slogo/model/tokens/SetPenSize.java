package slogo.model.tokens;

public class SetPenSize extends DisplayCommand {
    private static final int numArgs = 1;

    public SetPenSize(String name) {
        super(numArgs);
        instrName = name;
    }
}
