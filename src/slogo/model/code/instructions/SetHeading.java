package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        double prevAngle = t.getAngle();
//        t.setAngle(this.valueForExec);
//        return Math.abs(t.getAngle() - prevAngle);

        // bound vars code:
//        if (this.hasInnerInstruction()) {
//            valueForExec = possibleInner.execute(t, vars);
//        }
//        double prevAngle = t.getAngle();
//        t.setProperties(t.getXPos(), t.getYPos(), valueForExec, t.getIsPenUp());
//        return Math.abs(t.getAngle() - prevAngle);
        Token angleValue = this.parameters.get(0);
        if (angleValue instanceof Instruction) {
            ((Instruction) angleValue).execute(t);
        }
        int valueForExec = angleValue.generateValue();
        t.setProperties(t.getXPos(), t.getYPos(), valueForExec, t.getIsPenUp());
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }
    //TODO: needs to return the change in angle

    public String toString(){
        return "";
    }
}
