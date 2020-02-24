package slogo.model.code.instructions.math;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Sum extends Instruction {

    private static final int NUM_ARGS = 2;

//    @Override
//    public void execute (Turtle t) {
//        Token valueToMove = this.parameters.get(0);
//        if (valueToMove instanceof Instruction) {
//            ((Instruction) valueToMove).execute(t);
//        }
//        int valueForExec = valueToMove.generateValue();
//        double x = t.getXPos() + valueForExec * Math.cos(t.getAngle());
//        double y = t.getYPos() + valueForExec * Math.sin(t.getAngle());
//        t.setProperties(x, y, t.getAngle(), t.getIsPenUp());
//    }

    @Override
    public void execute (Turtle t) {
        Token firstVal = this.parameters.get(0);
        Token secondVal = this.parameters.get(1);
        if (firstVal instanceof Instruction) {
            ((Instruction) firstVal).execute(t);
        }
        if (secondVal instanceof Instruction) {
            ((Instruction) secondVal).execute(t);
        }
        int num1 = firstVal.generateValue();
        int num2 = secondVal.generateValue();
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    // needs to return num1 + num2 somehow
    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }

    public String toString(){
        return "";
    }
}
