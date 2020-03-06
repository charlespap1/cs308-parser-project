package slogo.model.tokens;

public class SetBackground extends DisplayCommand {
    private static final int numArgs = 1;

    public SetBackground(String name) {
        super(numArgs);
        instrName = name;
    }
}
