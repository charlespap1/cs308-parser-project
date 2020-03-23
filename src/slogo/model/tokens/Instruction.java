package slogo.model.tokens;

import slogo.model.TurtleMasterAccessor;
import slogo.model.exceptions.CommandCannotDoListException;
import slogo.model.exceptions.InvalidArgumentException;
import slogo.model.exceptions.InvalidLoopConditionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Class implementing Token interface extended by all executable commands. Contains functionality to
 * set parameters, set access to the TurtleMaster object, execute and convert parameters to values, and
 * run a loop.
 *
 * @author Charles, Natalie, Michael
 */
public abstract class Instruction implements Token {

    protected List<Token> parameters = new ArrayList<>();
    protected String instrName = "";
    private int NUM_ARGS;
    protected TurtleMasterAccessor myAccessor;

    /**
     * Constructs new Instruction, sets the number of arguments to given numArgs.
     * @param numArgs number of arguments this instruction takes to be executed
     */
    public Instruction(int numArgs) {
        NUM_ARGS = numArgs;
    }

    /**
     * Executes action of a given Instruction.
     * @return value of execution
     */
    public abstract double execute();

    /**
     * Gets number of arguments this Instruction needs to execute properly.
     * @return number of arguments (parameters)
     */
    public int numRequiredArgs() {
        return NUM_ARGS;
    }

    /**
     * Gives Instruction access to interface that allows interaction with TurtleMaster, used for commands
     * that are executed on all turtles, queries for a single turtle, and multi-turtle commands (Ask, Tell, etc.)
     * @param accessor interface granting access to methods in TurtleMaster
     */
    public void setAccessor(TurtleMasterAccessor accessor) {
        myAccessor = accessor;
    }

    /**
     * Sets Instruction parameters/arguments.
     * @param params instruction parameters
     */
    public void setParameters(List<Token> params) {
        parameters = params;
    }

    /**
     * Gets String representation of Instruction including name and parameters.
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(instrName);
        for (Token param : parameters) sb.append(" ").append(param.toString());
        return sb.toString();
    }

    protected List<Double> getParamsAsVals() {
        return getParamsAsVals(parameters);
    }

    protected List<Double> getParamsAsVals(List<Token> tokens) throws CommandCannotDoListException {
        List<Double> paramsAsDoubles = new ArrayList<>();
        for (Token currToken : tokens) {
            paramsAsDoubles.add(checkTokenNotListAndGetVal(currToken));
        }
        return paramsAsDoubles;
    }

    protected double checkTokenNotListAndGetVal(Token currToken) throws CommandCannotDoListException {
        if (currToken instanceof ListSyntax)
            throw new CommandCannotDoListException();
        return currToken.execute();
    }

    protected double runLoop(double start, double end, double increment, Token variable) {
        Token list2 = parameters.get(1);
        if (!(list2 instanceof ListSyntax)) throw new InvalidArgumentException();
        double returnValue = 0;
        List<Token> commands = ((ListSyntax) list2).getContents();
        for (double i = start; i <= end; i += increment) {
            if (variable != null) ((Variable) variable).setVariable(i);
            for (Token command : commands) {
                if (!(command instanceof Instruction)) throw new InvalidLoopConditionException();
                returnValue = command.execute();
            }
        }
        return returnValue;
    }
}
