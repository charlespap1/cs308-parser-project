package slogo.model.tokens;

public class PenUp extends DisplayCommand {
    private static final int numArgs = 0;

    public PenUp(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
