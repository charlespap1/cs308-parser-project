package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.TurtleAction;
import slogo.model.code.instructions.Instruction;
import slogo.model.parse.TurtleMasterAccessor;

import java.util.List;

public class Forward extends Instruction {

    private static final int numArgs = 1;

    public TurtleAction myAction = t -> {
        List<Double> paramsAsVals = getParamsAsVals();
        double valueForExec = paramsAsVals.get(0);
        double x = t.getXPos() - valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() - valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        return valueForExec;
    };

    public Forward(String name){
        super(numArgs);
        instrName = name;
    }

    public double execute(){
        return myAccessor.turtleCommandToMaster(myAction); }

//    protected void performAction (Turtle t) {
//        // TODO: change active turtle for query params
//        List<Double> paramsAsVals = getParamsAsVals();
//        double valueForExec = paramsAsVals.get(0);
//        valueOfExecution = valueForExec;
//        double x = t.getXPos() - valueForExec * Math.cos(Math.toRadians(t.getAngle()));
//        double y = t.getYPos() - valueForExec * Math.sin(Math.toRadians(t.getAngle()));
//        t.setLocation(x, y);
//    }
}
