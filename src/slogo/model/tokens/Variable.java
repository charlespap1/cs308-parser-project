package slogo.model.tokens;

import slogo.model.parse.AddToListFunction;

public class Variable implements Token {
    private double value = 0;
    private String name;
    private AddToListFunction function;

    public Variable(String name, AddToListFunction fn){
        this.name = name;
        function = fn;
    }

    public void setVariable(double var){
        value = var;
        function.addToList(this);
    }

    public double execute(){
        return value;
    }

    @Override
    public String toString(){
        return name;
    }
}
