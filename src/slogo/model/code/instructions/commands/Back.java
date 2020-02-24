package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class Back extends Instruction {

    private static final int numArgs = 1;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
        Token valueToMove = this.parameters.get(0);
        if (valueToMove instanceof Instruction) {
            ((Instruction) valueToMove).execute(t);
        }
        int valueForExec = valueToMove.generateValue();
        double x = t.getXPos() - valueForExec * Math.cos(t.getAngle());
        double y = t.getYPos() - valueForExec * Math.sin(t.getAngle());
        t.setLocation(x, y);
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
