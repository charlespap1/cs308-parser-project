package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Instruction implements Token {

    protected List<Token> parameters;
    protected String instrName;
    protected int valueOfExecution;
    private static final String ERROR_MESSAGE = "Parameter for this instruction cannot be a list";

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

    protected double distFrom(double x, double y, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x,2) + Math.pow(y2 - y,2));
    }

    protected List<Integer> getParamsAsVals(Turtle t){
        List<Integer> paramsAsInts = new ArrayList<>();
        for(Token currToken: parameters){
            assert !(currToken instanceof ListSyntax) : ERROR_MESSAGE;
            if(currToken instanceof Instruction){
                ((Instruction) currToken).execute(t);
            }
            paramsAsInts.add(currToken.generateValue());
        }
        return paramsAsInts;
    }

}
