package slogo.model.tokens;

import slogo.model.Turtle;

public class Heading extends Instruction {

    private static final int numArgs = 0;
    private TurtleAction myAction = Turtle::getAngle;

    public Heading(String name) {
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleQueryToMaster(myAction); }

}