package slogo.model.code;

import slogo.model.code.Code;

public class Constant implements Code {

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
