package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Towards extends Instruction {
    public static final int ANGLE_OFFSET = 90;
    private static final int numArgs = 2;

    public Towards(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = getParamsAsVals(t);
        // TODO: check types
        double xCord = paramsAsInts.get(0);
        double yCord = paramsAsInts.get(1);
        double angle = Math.atan2(xCord - t.getXPos(), yCord - t.getYPos());
        double angleDegrees = Math.toDegrees(angle);
        this.valueOfExecution = (int)(Math.abs(t.getAngle() - angleDegrees));
        t.setAngle(angleDegrees+ANGLE_OFFSET);
        t.setCurrCommand(toString(xCord, yCord));
        t.setCurrCommand("");
    }

    public String toString(double x, double y){
        return instrName + " " + x + ", " + y;
    }
}
