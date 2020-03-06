package slogo.model.tokens;

public class PenDown extends DisplayCommand {
    private static final int numArgs = 0;

    public PenDown(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute () {
        return myAction.execute(null);
    }
}
