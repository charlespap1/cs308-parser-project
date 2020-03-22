package slogo.model.tokens;

import slogo.model.parse.AddToListFunction;

/**
 * Variable Token class.
 * @author Charles, Natalie, Michael
 */
public class Variable implements Token {
    private double value;
    private String name;
    private AddToListFunction function;

    /**
     * Constructs new Variable.
     * @param name String name of command, used by toString
     * @param fn function to allow adding variable with new value to list of variables
     */
    public Variable(String name, AddToListFunction fn) {
        this.name = name;
        function = fn;
    }

    /**
     * Sets variable value, re-adds the variable to the variable list.
     * @param var the value to set the variable to
     */
    public void setVariable(double var) {
        value = var;
        function.addToList(this);
    }

    /**
     * Gets name of variable.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets value of variable.
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Executes to variable value.
     * @return value
     */
    @Override
    public double execute() {
        return value;
    }

    /**
     * String representation of variable is just variable name.
     * @return String name
     */
    @Override
    public String toString() {
        return name;
    }
}
