package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0;
    private static final int numArgs = 0;

    public SetHome(String name){
        super(numArgs);
        instrName = name;
    }

    public void performAction (Turtle t) {
        valueOfExecution = distFrom(t.getXPos(),t.getYPos(),HOME_X,HOME_Y);
        t.setLocation(HOME_X, HOME_Y);
    }

    private double distFrom(double x, double y, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x,2) + Math.pow(y2 - y,2));
    }

    @Override
    public String toString(){
        return instrName;
    }
}
