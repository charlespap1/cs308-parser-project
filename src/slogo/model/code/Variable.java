package slogo.model.code;

public class Variable implements Token {
    private double value = 0;
    private String name;

    public Variable(String name){
        this.name = name;
    }

    public void setVariable(double var){
        value = var;
    }

    public double generateValue(){
        return value;
    }

    public String toString(){
        return name + " " + value;
    }
}
