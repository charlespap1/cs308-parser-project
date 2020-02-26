package slogo.model.code;

public class NewCommandName implements Token {
    private String myName;

    public NewCommandName(String name){
        myName = name;
    }

    public double generateValue() {
        return 0;
    }

    public String toString(){ return myName;}
}
