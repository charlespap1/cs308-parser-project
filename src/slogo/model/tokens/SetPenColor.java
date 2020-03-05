package slogo.model.tokens;

public class SetPenColor extends DisplayCommand {
    private static final int numArgs = 1;

    public SetPenColor(String name) {
        super(numArgs);
        instrName = name;
    }
}
