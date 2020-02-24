package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class SetXY extends Instruction {

    private static final int NUM_ARGS = 2;

    public SetXY(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsInts = getParamsAsVals(t);
        int xCord = paramsAsInts.get(0);
        int yCord = paramsAsInts.get(1);
        this.valueOfExecution = (int)distFrom(xCord,yCord,t.getXPos(),t.getYPos());
        t.setLocation(xCord, yCord);
        t.setCurrCommand(toString(xCord + "",yCord + ""));
        t.setCurrCommand("");
    }

    public int numRequiredArgs(){
        return NUM_ARGS;
    }

    public String toString(){
        return instrName;
    }

    public String toString(String x, String y){
        return toString() + " " + x + "," + y;
    }
}
