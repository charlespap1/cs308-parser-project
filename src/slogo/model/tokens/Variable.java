package slogo.model.tokens;

import slogo.model.parse.AddToListFunction;

public class Variable implements Token {
    private double value;
    private String name;
    private AddToListFunction function;
    //private boolean printOnlyName = false;

    public Variable(String name, AddToListFunction fn){
        this.name = name;
        function = fn;
    }

    public void setVariable(double var){
        value = var;
        function.addToList(this);
    }

    public String getName () {
        return name;
    }

    public double execute(){
        return value;
    }

//    public void setPrintOnlyName (boolean printOnlyName) {
//        this.printOnlyName = printOnlyName;
//    }

    @Override
    public String toString(){
        return name;
    }
}
