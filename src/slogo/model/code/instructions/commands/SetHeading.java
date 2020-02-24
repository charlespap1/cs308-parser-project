package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;

    @Override
    public void execute (Turtle t) { //(Turtle t, Collection<Variable> vars)
        Token angleValue = this.parameters.get(0);
        if (angleValue instanceof Instruction) {
            ((Instruction) angleValue).execute(t);
        }
        int valueForExec = angleValue.generateValue();
        t.setAngle(valueForExec);
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public int generateValue(){
        return this.parameters.get(0).generateValue();
    }
    //TODO: needs to return the change in angle

    public String toString(){
        return "";
    }
}
