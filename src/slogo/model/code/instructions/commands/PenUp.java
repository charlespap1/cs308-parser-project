package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

public class PenUp extends Instruction {

    private static final int numArgs = 0;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
        //t.setProperties(t.getXPos(), t.getYPos(), t.getAngle(), true);
        t.setPenUp(true);
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
