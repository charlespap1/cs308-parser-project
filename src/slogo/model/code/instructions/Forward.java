package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Forward extends Instruction {

    private static final int NUM_ARGS = 1;

    @Override
    public void execute (Turtle t) {
        Token valueToMove = this.parameters.get(0);
        if (valueToMove instanceof Instruction) {
            ((Instruction) valueToMove).execute(t);
        }
        int valueForExec = valueToMove.generateValue();
        double x = t.getXPos() + valueForExec * Math.cos(t.getAngle());
        double y = t.getYPos() + valueForExec * Math.sin(t.getAngle());
        t.setProperties(x, y, t.getAngle(), t.getIsPenUp());
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
