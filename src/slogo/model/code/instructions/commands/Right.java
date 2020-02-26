package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Right extends Instruction {

    protected int NUM_ARGS = 1;

    public Right(String name){
        super();
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsVals = getParamsAsVals(t);
        // TODO: check types
        double valueForExec = paramsAsVals.get(0);
        this.valueOfExecution = valueForExec;
        double angle = t.getAngle() + valueForExec;
        t.setAngle(angle);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}