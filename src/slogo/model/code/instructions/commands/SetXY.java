package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class SetXY extends Instruction {

    private static final int numArgs = 2;

    public SetXY(String name){
        super(numArgs);
        instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = getParamsAsVals(t);
        double xCord = paramsAsInts.get(0);
        double yCord = paramsAsInts.get(1);
        valueOfExecution = distFrom(xCord,yCord,t.getXPos(),t.getYPos());
        t.setLocation(xCord, -yCord);
        t.setCurrCommand(toString(xCord, -yCord));
        t.setCurrCommand("");
    }

    public String toString(double x, double y){
        return instrName + " " + x + ", " + y;
    }
}
