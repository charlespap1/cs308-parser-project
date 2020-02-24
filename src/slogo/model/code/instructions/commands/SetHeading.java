package slogo.model.code.instructions.commands;

import slogo.model.Turtle;
import slogo.model.code.Token;
import slogo.model.code.instructions.Instruction;

import java.util.List;

public class SetHeading extends Instruction {

    private static final int numArgs = 1;

    public SetHeading(String name){
        super();
        this.instrName = name;
    }

    @Override
    public void execute (Turtle t) {
        List<Integer> paramsAsVals = getParamsAsVals(t);
        int valueForExec = paramsAsVals.get(0);
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
