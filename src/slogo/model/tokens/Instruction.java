package slogo.model.tokens;

import slogo.model.TurtleMasterAccessor;
import slogo.model.exceptions.CommandCannotDoListException;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction implements Token {

    protected List<Token> parameters = new ArrayList<>();
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
        StringBuilder sb = new StringBuilder(instrName);
        for (Token param:parameters) sb.append(" ").append(param.toString());
        return sb.toString();
    }

    protected double runLoop(double start, double end, double increment, Token variable){
        Token list2 = parameters.get(1);
        if(!(list2 instanceof ListSyntax)) throw new InvalidArgumentException();
        double returnValue = 0;
        List<Token> commands = ((ListSyntax) list2).getContents();
        for (double i = start; i <= end; i += increment) {
            if (variable!=null) ((Variable) variable).setVariable(i);
            for (Token command: commands) {
                if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
                returnValue = command.execute();
            }
        }
        return returnValue;
    }
}
