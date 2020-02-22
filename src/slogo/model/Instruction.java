package slogo.model;

import slogo.State;

import java.util.List;
import java.util.Map;

public abstract class Instruction {

    private int valueForExec;
    private Instruction possibleInner;

    public Instruction(int val){
        valueForExec = val;
        possibleInner = null;
    }

    public Instruction(Instruction i){
        valueForExec = -1;
        possibleInner = i;
    }

    public abstract int execute(Turtle turtle, List<Variable> vars);
    public abstract List<String> getNeededVarNames();
    public abstract int numRequiredArgs();

    protected boolean hasInnerInstruction(){
        return possibleInner != null;
    }
}
