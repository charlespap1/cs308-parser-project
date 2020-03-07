package slogo.model.tokens;


public class ClearScreen extends DisplayCommand {
    private static final int numArgs = 0;

    public ClearScreen(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
