package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Right extends Instruction {

    private static final int numArgs = 1;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setAngle(t.getAngle() - this.valueForExec);
//        return valueForExec;

        // bound vars code:
//        if (this.hasInnerInstruction()) {
//            valueForExec = this.possibleInner.execute(t, vars);
//        }
//        double angle = t.getAngle() - valueForExec;
//        t.setProperties(t.getXPos(), t.getYPos(), angle, t.getIsPenUp());
//        return valueForExec;
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