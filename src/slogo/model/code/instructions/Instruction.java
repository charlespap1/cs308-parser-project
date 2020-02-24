package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.Token;

import java.util.Collection;
import java.util.List;

public abstract class Instruction implements Token {

    protected List<Token> parameters;
    protected String instrName;
    protected int valueOfExecution;

    public Instruction(){
        parameters = null;
        instrName = "";
        valueOfExecution = 0;
    }

    public abstract void execute(Turtle turtle);
    public abstract int numRequiredArgs();

    public int generateValue() {
        return valueOfExecution;
    }

    public void setParameters(List<Token> params){
        parameters = params;
    }

    protected double distFromHome(double x, double y){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

}
