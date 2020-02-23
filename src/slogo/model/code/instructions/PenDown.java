package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenDown extends Instruction {

    private static final int numArgs = 0;

    @Override
    public int execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setPenUp(false);

        //bound vars code:
//        if (this.hasInnerInstruction()) {
//            valueForExec = possibleInner.execute(t, vars);
//        }
//        t.setProperties(t.getXPos(), t.getYPos(), t.getAngle(), false);
//        return 1;
        return 1;
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public int generateValue(){
        return 1;
    }

    public String toString(){
        return "Pen Down";
    }
}
