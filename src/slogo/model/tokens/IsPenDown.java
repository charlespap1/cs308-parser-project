package slogo.model.tokens;

public class IsPenDown extends DisplayCommand {
    private static final int numArgs = 0;

    public IsPenDown(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute () {
        return myAction.execute(null);
    }
}
