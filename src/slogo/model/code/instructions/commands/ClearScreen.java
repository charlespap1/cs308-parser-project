package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.display.DisplayCommand;

import java.awt.geom.Point2D;


public class ClearScreen extends DisplayCommand {

    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    public static final int DEFAULT_ROTATION = 90;
    private static final int numArgs = 0;

    public ClearScreen(String name){
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = Point2D.distance(t.getXPos(), t.getYPos(), HOME_X, HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
        t.setAngle(DEFAULT_ROTATION);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
        try{
            myAction.execute(null);
        } catch (Exception e){
            System.out.println("bad method");
        }
    }

    @Override
    public String toString(){
        return instrName;
    }
}
