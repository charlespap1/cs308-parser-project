package slogo.model;

public class Constant implements Code{

    private final int val;

    public Constant(int value){
        val = value;
    }

    public int generateValue() {
        return val;
    }

    public String toString(){
        return "";
    }
}
