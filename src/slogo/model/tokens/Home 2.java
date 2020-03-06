package slogo.model.tokens;

import java.awt.geom.Point2D;

public class Home extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int numArgs = 0;
    private TurtleAction myAction = t -> {
        double returnValue = Point2D.distance(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
        return returnValue;
    };

    public Home(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute(){ return myAccessor.turtleCommandToMaster(myAction); }

}
