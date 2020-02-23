package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0; // TODO: how to handle home/ (0,0) in center of window
    private static final int NUM_ARGS = 0;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        double xPos = t.getxPos();
//        double yPos = t.getyPos();
//        t.setxPos(0);
//        t.setyPos(0);
//        return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));

        // bound vars code:
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        double xPos = t.getXPos();
//        double yPos = t.getYPos();
//        t.setProperties(HOME_X, HOME_Y, t.getAngle(), t.getIsPenUp());
//        return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
        t.setProperties(HOME_X, HOME_Y, t.getAngle(), t.getIsPenUp());
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }
    //TODO: needs to return the distance the turtle moved

    public String toString(){
        return "";
    }
}
