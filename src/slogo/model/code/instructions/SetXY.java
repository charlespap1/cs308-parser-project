package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;

public class SetXY extends Instruction {

    private static final int NUM_ARGS = 2;

    @Override
    public void execute (Turtle t) {
        Token xCoordToRelocate = this.parameters.get(0);
        Token yCoordToRelocate = this.parameters.get(1);
        if (xCoordToRelocate instanceof Instruction) {
            ((Instruction) xCoordToRelocate).execute(t);
        }
        if (yCoordToRelocate instanceof Instruction) {
            ((Instruction) yCoordToRelocate).execute(t);
        }
        int xCoord = xCoordToRelocate.generateValue();
        int yCoord = yCoordToRelocate.generateValue();
        t.setProperties(xCoord, yCoord, t.getAngle(), t.getIsPenUp());
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
