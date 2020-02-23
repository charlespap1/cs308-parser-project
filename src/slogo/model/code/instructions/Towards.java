package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;

public class Towards extends Instruction {

    private static final int NUM_ARGS = 2;

    @Override
    public void execute (Turtle t) {
        Token xCoordToFace = this.parameters.get(0);
        Token yCoordToFace = this.parameters.get(1);
        if (xCoordToFace instanceof Instruction) {
            ((Instruction) xCoordToFace).execute(t);
        }
        if (yCoordToFace instanceof Instruction) {
            ((Instruction) yCoordToFace).execute(t);
        }
        int xCoord = xCoordToFace.generateValue();
        int yCoord = yCoordToFace.generateValue();
        double angle = Math.atan2(yCoord - t.getYPos(), xCoord - t.getXPos());
        t.setProperties(t.getXPos(), t.getYPos(), angle, t.getIsPenUp());
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}
