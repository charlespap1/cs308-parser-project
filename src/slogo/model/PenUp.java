package slogo.model;

import java.util.List;

public class PenUp extends Instruction {

    private static final int numArgs = 0;

    @Override
    public int execute (Turtle t) {
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setPenUp(true);
        return 0;
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public int generateValue(){
        return 0;
    }

    public String toString(){
        return "Pen Up";
    }
}
