package slogo.model;

import java.util.List;

public class Left extends Instruction {

    private static final int numArgs = 1;

    public Left () {
        super();
    }

    @Override
    public int execute (Turtle t) {
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setAngle(t.getAngle() + this.valueForExec);
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
