package slogo.model.tokens.instructions.commands;

import slogo.model.tokens.instructions.TurtleAction;
import slogo.model.tokens.instructions.Instruction;

import java.awt.geom.Point2D;
import java.util.List;

public class SetPosition extends Instruction {

    private static final int numArgs = 2;private TurtleAction myAction = t -> {
        List<Double> paramsAsInts = getParamsAsVals();
        double xCord = paramsAsInts.get(0);
        double yCord = paramsAsInts.get(1);
        double returnValue = Point2D.distance(xCord,yCord,t.getXPos(),t.getYPos());
        t.setLocation(xCord, -yCord);
        return returnValue;
    };


    public SetPosition(String name){
        super(numArgs);
        instrName = name;
    }

    public String toString(double x, double y){
        return instrName + " " + x + " " + y;
    }

    @Override
    public double execute(){ return myAccessor.turtleCommandToMaster(myAction); }

}
