package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;

    public SetHeading(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = getParamsAsVals(t);
        double valueForExec = paramsAsVals.get(0);
        valueOfExecution = Math.abs(t.getAngle() - valueForExec);
        t.setAngle(valueForExec);
        t.setCurrCommand(toString(valueForExec));
        t.setCurrCommand("");
    }

    public String toString(double heading){
        return instrName + " " + heading;
    }
}
