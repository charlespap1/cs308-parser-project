package slogo.model.code.instructions.commands;

import slogo.model.TurtleAction;
import slogo.model.code.instructions.Instruction;

import java.awt.geom.Point2D;
import java.util.List;

public class SetXY extends Instruction {

    private static final int numArgs = 2;private TurtleAction myAction = t -> {
        List<Double> paramsAsInts = getParamsAsVals();
        double xCord = paramsAsInts.get(0);
        double yCord = paramsAsInts.get(1);
        double returnValue = Point2D.distance(xCord,yCord,t.getXPos(),t.getYPos());
        t.setLocation(xCord, -yCord);
        return returnValue;
    };


    public SetXY(String name){
        super(numArgs);
        instrName = name;
    }

    public String toString(double x, double y){
        return instrName + " " + x + " " + y;
    }

    @Override
    public double execute(){ return myAccessor.turtleCommandToMaster(myAction); }

}
