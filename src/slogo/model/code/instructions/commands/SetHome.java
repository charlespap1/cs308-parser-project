package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.awt.geom.Point2D;

public class SetHome extends TurtleCommand {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int numArgs = 0;

    public SetHome(String name){
        super(numArgs);
        instrName = name;
    }

    protected void performAction (Turtle t) {
        valueOfExecution = Point2D.distance(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
    }

    @Override
    public String toString(){
        return instrName;
    }
}
