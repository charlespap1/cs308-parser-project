package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import slogo.model.code.exceptions.CommandCannotDoListException;

public abstract class Instruction implements Token {

    protected List<Token> parameters = null;
    protected String instrName = "";
    protected double valueOfExecution = 0;
    private int NUM_ARGS;

    public Instruction(int numArgs){
        NUM_ARGS = numArgs;
    }

    public abstract void execute();

    public int numRequiredArgs() { return NUM_ARGS; }

    public double generateValue() {
        return valueOfExecution;
    }

    public void setParameters(List<Token> params){
        parameters = params;
    }

    protected List<Double> getParamsAsVals() throws CommandCannotDoListException {
        List<Double> paramsAsDoubles = new ArrayList<>();
        for (Token currToken: parameters) {
            paramsAsDoubles.add(checkTokenNotListAndGetVal(currToken));
        }
        return paramsAsDoubles;
    }

    protected double checkTokenNotListAndGetVal(Token currToken) throws CommandCannotDoListException {
        if (currToken instanceof ListSyntax) {
            throw new CommandCannotDoListException();
        } else if (currToken instanceof Instruction){
            ((Instruction) currToken).execute();
        }
        return currToken.generateValue();
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
