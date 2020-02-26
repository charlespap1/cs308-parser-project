package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Towards extends Instruction {

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
        System.out.println(angleDegrees);
        this.valueOfExecution = (int)(Math.abs(t.getAngle() - angleDegrees));
        t.setAngle(angleDegrees+90);
        t.setCurrCommand(toString(xCord + "", yCord + ""));
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }

    // TODO: why is this public?
    public String toString(String x, String y){
        return this.toString() + " " + x + "," + y;
    }
}
