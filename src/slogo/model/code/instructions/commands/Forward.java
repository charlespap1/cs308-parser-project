package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.exceptions.ListNotIntegerException;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Forward extends Instruction {

    private static final int numArgs = 1;

    public Forward(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) throws ListNotIntegerException {
        List<Double> paramsAsVals = getParamsAsVals(t);
        double valueForExec = paramsAsVals.get(0);
        // TODO: check types
        valueOfExecution = valueForExec;
        double x = t.getXPos() - valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() - valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }
}
