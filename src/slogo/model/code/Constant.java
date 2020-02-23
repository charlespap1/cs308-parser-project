package slogo.model.code;

public class Constant implements Token {

    private final int val;

    public Constant(String value){
        val = Integer.parseInt(value);
        //TODO: error handling
    }

    public int generateValue() {
        return val;
    }

    public String toString(){
        return "";
    }
}
