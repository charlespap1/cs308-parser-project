package slogo.model.code;

public class Variable implements Token {

    private int value;
    private String name;

    public Variable(String name){
        this.name = name;
        value = 0;
    }

    public void setVariable(int var){
        value = var;
    }

    public int generateValue(){
        return value;
    }

    public String toString(){
        return name + ": " + value;
    }
}
