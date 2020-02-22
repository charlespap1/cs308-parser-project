package slogo.model.code;

public class Constant implements Token {

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
