package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class PenDown extends Instruction {

    private static final int numArgs = 0;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
        //t.setProperties(t.getXPos(), t.getYPos(), t.getAngle(), false);
        t.setPenUp(false);
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
