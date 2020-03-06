package slogo.model.tokens;

public class GetShape extends DisplayCommand {
    private static final int numArgs = 0;

    public GetShape(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute () {
        return myAction.execute(null);
    }
}
