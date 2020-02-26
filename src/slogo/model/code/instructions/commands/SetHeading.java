package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class SetHeading extends Instruction {

    protected int NUM_ARGS = 1;

    public SetHeading(String name){
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = getParamsAsVals(t);
        double valueForExec = paramsAsVals.get(0);
        valueOfExecution = Math.abs(t.getAngle() - valueForExec);
        t.setAngle(valueForExec);
        // TODO: better stringifying
        t.setCurrCommand(toString(valueForExec + ""));
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }

    public String toString(String heading){
        return toString() + " " + heading;
    }
}
