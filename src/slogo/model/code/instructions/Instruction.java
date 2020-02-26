package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Instruction implements Token {
    // TODO: hard coded text
    private static final String ERROR_MESSAGE = "Parameter for this instruction cannot be a list";

    protected List<Token> parameters = null;
    protected String instrName = "";
    protected double valueOfExecution = 0;
    private int NUM_ARGS;

    public Instruction(int numArgs){
        NUM_ARGS = numArgs;
    }

    public abstract void execute(Turtle turtle);

    public int numRequiredArgs() { return NUM_ARGS; }

    public double generateValue() {
        return valueOfExecution;
    }

    public void setParameters(List<Token> params){
        parameters = params;
    }

    protected double distFrom(double x, double y, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x,2) + Math.pow(y2 - y,2));
    }

    protected List<Double> getParamsAsVals(Turtle t){
        List<Double> paramsAsDoubles = new ArrayList<>();
        for (Token currToken: parameters){
            assert !(currToken instanceof ListSyntax) : ERROR_MESSAGE;
            if (currToken instanceof Instruction){
                ((Instruction) currToken).execute(t);
            }
            paramsAsDoubles.add(currToken.generateValue());
        }
        return paramsAsDoubles;
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
