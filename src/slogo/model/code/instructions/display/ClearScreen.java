package slogo.model.code.instructions.display;

import slogo.model.Turtle;
import slogo.view.DisplayAction;

import java.awt.geom.Point2D;


public class ClearScreen extends DisplayCommand {
    private static final int numArgs = 0;

    public ClearScreen(String name){
        super(numArgs);
        instrName = name;
    }

    @Override
    public double execute () {
        double distanceMoved = -1;
        try{
            distanceMoved = myAction.execute(null);
        } catch (Exception e){
            // TODO: error handling
            System.out.println("bad method");
        }
        return distanceMoved;
    }
}
