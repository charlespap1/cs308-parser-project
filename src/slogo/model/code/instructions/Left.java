package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Left extends Instruction {

    private static final int numArgs = 1;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
        Token valueToRotate = this.parameters.get(0);
        if (valueToRotate instanceof Instruction) {
            ((Instruction) valueToRotate).execute(t);
        }
        int valueForExec = valueToRotate.generateValue();
        double angle = t.getAngle() + valueForExec;
        t.setProperties(t.getXPos(), t.getYPos(), angle, t.getIsPenUp());
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
