package slogo.model;

import java.util.List;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;

    @Override
    public int execute (Turtle t) {
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        double prevAngle = t.getAngle();
//        t.setAngle(this.valueForExec);
//        return Math.abs(t.getAngle() - prevAngle);
        return 0;
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
