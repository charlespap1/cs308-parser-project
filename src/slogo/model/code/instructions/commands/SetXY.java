package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class SetXY extends Instruction {

    private static final int numArgs = 2;

    public SetXY(String name){
        super(numArgs);
        this.instrName = name;
    }

    public void execute (Turtle t) {
        List<Double> paramsAsInts = getParamsAsVals(t);
        // TODO: check types
        double xCord = paramsAsInts.get(0);
        double yCord = paramsAsInts.get(1);
        this.valueOfExecution = distFrom(xCord,yCord,t.getXPos(),t.getYPos());
        t.setLocation(xCord, -yCord);
        // TODO: better stringifying
        t.setCurrCommand(toString(xCord + "",yCord + ""));
        t.setCurrCommand("");
    }

    @Override
    public String toString(){
        return instrName;
    }

    public String toString(String x, String y){
        return toString() + " " + x + "," + y;
    }
}
