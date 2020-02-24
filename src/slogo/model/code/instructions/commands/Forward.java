package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class Forward extends Instruction {

    private static final int NUM_ARGS = 1;

    public Forward(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsVals = getParamsAsVals(t);
        int valueForExec = paramsAsVals.get(0);
        this.valueOfExecution = valueForExec;
        double x = t.getXPos() + valueForExec * Math.cos(Math.toRadians(t.getAngle()));
        double y = t.getYPos() + valueForExec * Math.sin(Math.toRadians(t.getAngle()));
        t.setLocation(x, y);
        t.setCurrCommand(toString());
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
