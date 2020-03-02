package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Towards extends Instruction {
    public static final int ANGLE_OFFSET = 90;
    private static final int numArgs = 2;

    public Towards(String name){
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        List<Double> paramsAsVals = getParamsAsVals(t);
        double xCord = paramsAsVals.get(0);
        double yCord = paramsAsVals.get(1);
        double angle = Math.atan2(xCord - t.getXPos(), yCord - t.getYPos());
        double angleDegrees = Math.toDegrees(angle);
        valueOfExecution = Math.abs(t.getAngle() - angleDegrees);
        t.setAngle(angleDegrees+ANGLE_OFFSET);
        t.setCurrCommand(toString(xCord, yCord));
        t.setCurrCommand("");
    }

    public String toString(double x, double y){
        return instrName + " " + x + " " + y;
    }
}
