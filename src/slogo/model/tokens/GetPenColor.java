package slogo.model.tokens;

public class GetPenColor extends DisplayCommand {
    private static final int numArgs = 0;

    public GetPenColor(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAction.execute(null);
    }
}
