package slogo.model.tokens;

public class IsPenDown extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.getIsPenUp() ? 0 : 1;

    public IsPenDown(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }

}
