package slogo.model;

import slogo.State;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class Instruction {

    protected double valueForExec;
    protected Instruction possibleInner;

    public Instruction(double val){
        valueForExec = val;
        possibleInner = null;
    }

    public Instruction(Instruction i){
        valueForExec = -1;
        possibleInner = i;
    }

    public abstract double execute(Turtle turtle, Collection<Variable> vars);
    public abstract List<String> getNeededVarNames();

    protected boolean hasInnerInstruction(){
        return possibleInner != null;
    }
}
