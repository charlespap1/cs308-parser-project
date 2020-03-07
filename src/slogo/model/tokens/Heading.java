package slogo.model.tokens;


public class Heading extends Instruction {
    public static final int ANGLE_OFFSET = 90;

    private static final int numArgs = 0;
    private TurtleAction myAction = t -> t.getAngle() - ANGLE_OFFSET;

    public Heading(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute() {
        return myAccessor.turtleQueryToMaster(myAction);
    }

}
