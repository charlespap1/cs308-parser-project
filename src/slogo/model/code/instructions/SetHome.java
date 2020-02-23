package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {
    public static final int HOME_X = 0;
    public static final int HOME_Y = 0; // TODO: how to handle home/ (0,0) in center of window
    private static final int numArgs = 1;

    @Override
    public int execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
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
        return -1;
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}
