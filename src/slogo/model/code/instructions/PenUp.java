package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenUp extends Instruction {

    private static final int numArgs = 0;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
//        if (this.hasInnerInstruction()) {
//            this.valueForExec = this.possibleInner.execute(t, vars);
//        }
//        t.setPenUp(true);

        // bound vars code:
//        if (this.hasInnerInstruction()) {
//            valueForExec = possibleInner.execute(t, vars);
//        }
//        t.setProperties(t.getXPos(), t.getYPos(), t.getAngle(), true);
//        return 0;
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
    // TODO: why is this here? should be from resource file
}
