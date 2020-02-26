package slogo.model.code;

public class Variable implements Token {
    private double value;
    private String name;

    public Variable(String name){
        this.name = name;
        value = 0;
    }

    public void setVariable(double var){
        value = var;
    }

    public double generateValue(){
        return value;
    }

    public String toString(){
        return name + ": " + value;
    }
}
