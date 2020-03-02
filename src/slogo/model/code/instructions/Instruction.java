package slogo.model.code.instructions;

import slogo.model.Turtle;
import slogo.model.code.ListSyntax;
import slogo.model.code.Token;

import java.util.ArrayList;
import java.util.List;
import slogo.model.code.exceptions.CommandCannotDoListException;

public abstract class Instruction implements Token {
    // TODO: hard coded text

    protected List<Token> parameters = null;
    protected String instrName = "";
    protected double valueOfExecution = 0;
    private int NUM_ARGS;

    public Instruction(int numArgs){
        NUM_ARGS = numArgs;
    }

    public void execute(List<Turtle> activeTurtles) {
        for (Turtle activeTurtle : activeTurtles) {
            performAction(activeTurtle);
        }
    }

    public abstract void performAction(Turtle turtle);

    public int numRequiredArgs() { return NUM_ARGS; }

    public double generateValue() {
        return valueOfExecution;
    }

    public void setParameters(List<Token> params){
        parameters = params;
    }

    protected List<Double> getParamsAsVals(Turtle t) throws CommandCannotDoListException {
        List<Double> paramsAsDoubles = new ArrayList<>();
        for (Token currToken: parameters) {
            paramsAsDoubles.add(checkTokenNotListAndGetVal(currToken, t));
        }
        return paramsAsDoubles;
    }

    protected double checkTokenNotListAndGetVal(Token currToken, Turtle t) throws CommandCannotDoListException {
        if (currToken instanceof ListSyntax) {
            throw new CommandCannotDoListException();
        } else if (currToken instanceof Instruction){
            ((Instruction) currToken).performAction(t);
        }
        return currToken.generateValue();
    }

    public String toString(){
        return instrName + " " + valueOfExecution;
    }
}
