package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;

import java.util.Collection;
import java.util.List;

public abstract class Instruction implements Token {

    protected List<Token> parameters;

    public Instruction(){
        parameters = null;
    }

    public abstract int execute(Turtle turtle); //Collection<Variable> vars)
    public abstract int numRequiredArgs();

    public void setParameters(List<Token> params){
        parameters = params;
    }
}

// previous instruction code for reference:
//    protected double valueForExec;
//    protected slogo.model.Instruction possibleInner;
//
//    public Instruction(double val){
//        valueForExec = val;
//        possibleInner = null;
//    }
//
//    public Instruction(slogo.model.Instruction i){
//        valueForExec = -1;
//        possibleInner = i;
//    }
//
//    public abstract double execute(Turtle turtle, Collection<Variable> vars);
//    public abstract List<String> getNeededVarNames();
//
//    protected boolean hasInnerInstruction(){
//        return possibleInner != null;
//    }

