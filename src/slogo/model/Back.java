package slogo.model;

import java.util.List;

public class Back extends Instruction {

    private static final int numArgs = 1;

    public Back () {
        super();
    }

    @Override
    public int execute (Turtle t) {
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setxPos(t.getxPos() - valueForExec * Math.cos(t.getAngle()));
//        t.setyPos(t.getyPos() - valueForExec * Math.sin(t.getAngle()));
//        return valueForExec;
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
