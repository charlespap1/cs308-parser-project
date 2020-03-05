package slogo.model.code.instructions;

import slogo.model.code.ListSyntax;
import slogo.model.code.Token;

import java.util.ArrayList;
import java.util.List;
import slogo.model.code.exceptions.CommandCannotDoListException;
import slogo.model.TurtleMasterAccessor;

public abstract class Instruction implements Token {

    protected List<Token> parameters = null;
    protected String instrName = "";
    private int NUM_ARGS;
    protected TurtleMasterAccessor myAccessor;

    public Instruction(int numArgs){
        NUM_ARGS = numArgs;
    }

    public abstract double execute();

    public int numRequiredArgs() { return NUM_ARGS; }

    public void setAccessor(TurtleMasterAccessor accessor) { myAccessor = accessor; }

    public void setParameters(List<Token> params){
        parameters = params;
    }

    protected List<Double> getParamsAsVals() {
        return getParamsAsVals(parameters);
    }

    protected List<Double> getParamsAsVals(List<Token> tokens) throws CommandCannotDoListException {
        List<Double> paramsAsDoubles = new ArrayList<>();
        for (Token currToken: tokens) {
            paramsAsDoubles.add(checkTokenNotListAndGetVal(currToken));
        }
        return paramsAsDoubles;
    }

    protected double checkTokenNotListAndGetVal(Token currToken) throws CommandCannotDoListException {
        if (currToken instanceof ListSyntax)
            throw new CommandCannotDoListException();
        return currToken.execute();
    }

    public String toString(){
        return instrName;
    }
}
