package slogo.model;

public class Variable implements Code {

    private int value;

    public Variable(){
        value = 0;
    }

    public void setVariable(int var){
        value = var;
    }

    public int generateValue(){
        return value;
    }

    public String toString(){
        return "";
    }
}
