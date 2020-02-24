package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;

    public SetHeading(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        Token angleValue = this.parameters.get(0);
        if (angleValue instanceof Instruction) {
            ((Instruction) angleValue).execute(t);
        }
        int valueForExec = angleValue.generateValue();
        this.valueOfExecution = (int)Math.abs(t.getAngle() - valueForExec);
        t.setAngle(valueForExec);
        t.setCurrCommand(toString(valueForExec + ""));
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return numArgs;
    }

    public String toString(){
        return instrName;
    }

    public String toString(String heading){
        return toString() + " " + heading;
    }
}
