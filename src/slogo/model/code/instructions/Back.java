package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class Back extends Instruction {

    private static final int numArgs = 1;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setxPos(t.getxPos() - valueForExec * Math.cos(t.getAngle()));
//        t.setyPos(t.getyPos() - valueForExec * Math.sin(t.getAngle()));
//        return valueForExec;

        // here is the code that works for bound variables:
//        if (this.hasInnerInstruction()) {
//            valueForExec = possibleInner.execute(t, vars);
//        }
//        double x = t.getXPos() - valueForExec * Math.cos(Math.toRadians(t.getAngle()));
//        double y = t.getYPos() - valueForExec * Math.sin(Math.toRadians(t.getAngle()));
//        t.setLocation(x, y);
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
