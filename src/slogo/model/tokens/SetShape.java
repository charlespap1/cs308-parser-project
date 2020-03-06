package slogo.model.tokens;

public class SetShape extends DisplayCommand {
    private static final int numArgs = 1;

    public SetShape(String name) {
        super(numArgs);
        instrName = name;
    }
}
