package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class SetHome extends Instruction {

    private static final int numArgs = 1;

    @Override
    public int execute (Turtle t) {
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        double xPos = t.getxPos();
//        double yPos = t.getyPos();
//        t.setxPos(0);
//        t.setyPos(0);
//        return Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));
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
